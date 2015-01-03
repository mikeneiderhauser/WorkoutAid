#include "defs_types.h"

/* Include Libs */
#include <SSD1306ASCII.h>

#include "Wire.h"
#include "MAX17043.h"

/* Instance of MAX17043 */
MAX17043 batMon;

/* Instance of SSD1306 */
SSD1306ASCII oled(OLED_MOSI, OLED_CLK, OLED_DC, OLED_RESET, OLED_CS);

mute_state_t mute_state = MUTE_ENABLED;
player_state_t player_state = PLAYER_STOPPED;

uint8_t time_hrs = 0;
uint8_t time_mins = 0;

buttons_state_t buttons_enabled = BUTTONS_ENABLED;       /* Indicates the buttons can be pushed */
uint8_t current_button_state = 0;  /* Current i/o button state */
uint8_t prev_button_state = 0;     /* Previous i/o button state */

float bat_per_cur = 0;
float bat_per_prev = 0;
long bat_last_read_time = 0;

uint8_t do_process_cmd = 0;
uint8_t android_state = 0;         /* Android state machine value */
uint8_t serial_buffer[SERIAL_BUF_SIZE];

void setup() {
  Serial.begin(9600);
  /* Set buttons as input. No need to debounce... done in hardware */
  pinMode(BTN_0, INPUT);
  pinMode(BTN_1, INPUT);
  pinMode(BTN_2, INPUT);
  pinMode(BTN_3, INPUT);
  pinMode(BTN_4, INPUT);
  pinMode(BTN_5, INPUT);

  /* OLED INIT */
  oled.ssd1306_init(SSD1306_SWITCHCAPVCC);
  oled.setCursor(0,0);
  oled.clear();

  /* BATTERY MONITOR INIT */
  Wire.begin(); 
  batMon.reset();
  batMon.quickStart();
  delay(1000);  // Wait for battery monitor to calculate

  /* Display default time */
  oled_display_time(time_hrs, time_mins);

  /* Display default mute state */
  oled_display_mute(mute_state);

  /* Display default player state */
  oled_display_player(player_state);

  /* Read battery State and print to display */
  bat_per_cur = batMon.getSoC();
  bat_per_prev = bat_per_cur;
  bat_last_read_time = millis();
  oled_display_battery((uint8_t)bat_per_cur);

  // oled default content Lines
  oled_display_line(1, F("Line 1"));
  oled_display_line(2, F("Line 2"));
  oled_display_line(3, F("Line 3"));
  oled_display_line(4, F("Line 4"));
  oled_display_line(5, F("BTN 1 | BTN 2 | BTN 3"));
  oled_display_line(6, F("---------------------"));
  oled_display_line(7, F("BTN 4 | BTN 5 | BTN 6"));

  current_button_state = read_buttons();
  prev_button_state = current_button_state;

  Serial.flush();
}

/* Main Loop */
void loop() {
  process_commands();
  read_serial();
  process_battery();
  process_buttons();

}

void oled_display_time(uint8_t h, uint8_t m) {
  // TODO imporve
  // Display Time
  time_hrs = h;
  time_mins = m;
  oled.home();
  oled.print("      ");
  oled.home();
  if(h < 128) {
    if(h >= 12) {
      if(h == 12) {
        oled.print(h);
      }
      else {
        oled.print(h-12);
      }
      oled.print(F(":"));
      if(m < 10) {
        oled.print(F("0"));
      } 
      oled.print(m);
      oled.print(F("P"));
    } 
    else {
      if(h == 0) {
        oled.print(12);
      } 
      else {
        oled.print(h);
      }
      oled.print(F(":"));
      if(m < 10) {
        oled.print(F("0"));
      }
      oled.print(m);
      oled.print(F("A"));
    }
  } 
  else {  // TIME_FORMAT_24
    oled.print(h & 0b01111111);
    oled.print(F(":"));
    if(m < 10) {
      oled.print(F("0"));
    }
    oled.print(m);
    oled.print(F("M"));
  } 
}

void oled_display_mute(mute_state_t m) {
  mute_state = m;
  oled.setCursor(MUTE_CHAR_OFFSET, STATUS_LINE_NUM);
  if(m==MUTE_DISABLED) {
    oled.print(F(" "));
  } 
  else if (m == MUTE_ENABLED){
    oled.print(F("M"));
  } 
  else {
    oled.print(F("E"));
  }
}

void oled_display_player(player_state_t p) {
  player_state = p;
  oled.setCursor(PLAYER_CHAR_OFFSET, STATUS_LINE_NUM);
  if(p == PLAYER_PLAYING) {
    oled.print(F("Pl"));
  } 
  else if(p == PLAYER_PAUSED) {
    oled.print(F("Pa"));
  } 
  else if (p == PLAYER_STOPPED) {
    oled.print(F("St"));
  } 
  else {
    oled.print(F("Er"));
  }
}

void oled_display_battery(int bat_per) {
  oled.setCursor(BATTERY_CHAR_OFFSET,STATUS_LINE_NUM);
  oled.print(bat_per);
  oled.print("%"); 
}

void oled_display_line(uint8_t line, char* buf) {
  oled_clear_line(line); // TODO - Needed?
  oled.setCursor(0, line);
  oled.print(buf);
}

void oled_display_line(uint8_t line, const __FlashStringHelper* buf) {
  //oled_display_line(line, (char*)buf[0]);
  oled_clear_line(line); // TODO - Needed?
  oled.setCursor(0, line);
  oled.print(buf);
}

void oled_clear_line(uint8_t line) {
  uint8_t chars = 0;
  oled.setCursor(0, line);
  for(chars = 0; chars < CHARS_PER_LINE; chars++) {
    oled.print(" ");
  }
}

void process_commands() {  
  // if no command to process.. return from function call
  if(do_process_cmd == 0) return;

  if(serial_buffer[0] == CMD_SET_LINE)
  {
    /*
        buf[0] = cmd      
     buf[1] = line num
     buf[2..24] = line data
     buf[25,26,27] = 0xA, 0xD, 0x0
     */

    serial_buffer[25] = 0;  // null term for sting
    if(serial_buffer[1] >= 1 && serial_buffer[1] <= 7)
    {
      oled_display_line(serial_buffer[1], (char*)&serial_buffer[2]);
    }
    else
    {
      // Send error back to Android
      Serial.write(CMD_ERROR);
      // Expecting a bit much here.. Can re-work later
      Serial.write(0xA);
      Serial.write(0xD);
      Serial.write(0x0);
    }
  }
  else if(serial_buffer[0] == CMD_SET_MUTE)
  {
    /*
        buf[0] = cmd
     buf[1] = state (MUTE_DISABLED, MUTE_ENABLED)
     buf[2,3,4]  = 0xA, 0xD, 0x0
     */
    oled_display_mute((mute_state_t)serial_buffer[1]);
  }
  else if(serial_buffer[0] == CMD_SET_PLAY)
  {
    /*
        buf[0] = cmd
     buf[1] = state (PLAYER_PLAYING, PLAYER_PAUSED, PLAYER_STOPPED)
     buf[2,3,4]  = 0xA, 0xD, 0x0
     */
    oled_display_player((player_state_t)serial_buffer[1]);
  }
  else if(serial_buffer[0] == CMD_SET_TIME)
  {
    /*
        buf[0] = cmd
     buf[1] = time hours
     buf[2] = time mins
     buf[3,4,5]  = 0xA, 0xD, 0x0
     */
    oled_display_time(serial_buffer[1], serial_buffer[2]);
  }
  else if(serial_buffer[0] == CMD_SET_STATE)
  {
    /*
        buf[1] = state (uint8)
     buf[2,3,4]  = 0xA, 0xD, 0x0
     */
    android_state = serial_buffer[1];
  }
  else if(serial_buffer[0] == CMD_GET_BAT)
  {
    /*
        buf[0] = cmd
     buf[1,2,3] = 0xA, 0xD, 0x0
     */
    // No need to re-read battery state
    // Send over BT (uint8_t)bat_per_cur
    Serial.write(CMD_BAT_UPDATE);
    Serial.write(android_state);
    Serial.write((uint8_t)bat_per_cur);
    // Expecting a bit much here.. Can re-work later
    Serial.write(0xA);
    Serial.write(0xD);
    Serial.write(0x0);
  }
  else // Command not recgonized
  {
    // Send error back to Android
    Serial.write(CMD_ERROR);
    // Expecting a bit much here.. Can re-work later
    Serial.write(0xA);
    Serial.write(0xD);
    Serial.write(0x0);
  }

  do_process_cmd = 0;

  for(int i = 0; i<SERIAL_BUF_SIZE; i++)
  {
    serial_buffer[i] = 0; 
  }
  Serial.flush();
}

void read_serial() {
  uint8_t itr = 0;

  if(Serial.available() > 0)
  {
    do_process_cmd = 1;
    serial_buffer[0] = Serial.read();

    switch ((commands_t)serial_buffer[0])
    {
    case CMD_SET_LINE:  // Tested all 7 lines
      Serial.readBytes((char *)&serial_buffer[1], 26); // line + 21 chars + NULL + (0xA + 0xD + NULL)
      break;
    case CMD_SET_STATE:  // Tested
    case CMD_SET_MUTE:   // Tested
    case CMD_SET_PLAY:   // Tested
      Serial.readBytes((char *)&serial_buffer[1], 4);
      break;
    case CMD_SET_TIME:   // Tested
      Serial.readBytes((char *)&serial_buffer[1], 5);
      break;
    case CMD_GET_BAT:  // Tested
      Serial.readBytes((char *)&serial_buffer[1], 3);
      break;
    case CMD_BTN_STATE:  // Tested 
      // Only set to Android. No need to process
      break;
    case CMD_BAT_UPDATE: // Tested
      // Only set to Android. No need to process
      break;
    case CMD_ERROR:  // Not tested
      // TODO - Determine Behav
      break;
    default:
      // cmd not recgonized
      do_process_cmd = 0;
      break;
    }
  }
}

void process_battery() {
  long time = millis();
  if(time >= BAT_READ_SEC + bat_last_read_time) {
    bat_last_read_time = time;
    bat_per_cur = batMon.getSoC();
    if((uint8_t)bat_per_cur != (uint8_t)bat_per_prev) {
      bat_per_prev = bat_per_cur;
      oled_display_battery((uint8_t)bat_per_cur);
      // Send over BT (uint8_t)bat_per_cur
      Serial.write(CMD_BAT_UPDATE);
      Serial.write(android_state);
      Serial.write((uint8_t)bat_per_cur);
      // Expecting a bit much here.. Can re-work later
      Serial.write(0xA);
      Serial.write(0xD);
      Serial.write(0x0);
    }
  } 
}

void process_buttons() {
  if(buttons_enabled == BUTTONS_ENABLED) {
    current_button_state = read_buttons();  /* Current i/o button state */
    if(current_button_state !=  prev_button_state) {
      prev_button_state = current_button_state; 
      // Send over BT (command, dev_state, buttons)
      Serial.write(CMD_BTN_STATE);
      Serial.write(android_state);
      Serial.write(current_button_state);
      // Expecting a bit much here.. Can re-work later
      Serial.write(0xA);
      Serial.write(0xD);
      Serial.write(0x0);
    }
  }
}

uint8_t read_buttons() {
  uint8_t button_state=0;
  //oled.setCursor(0, 2);
  /* Using bitClear and bitSet so the code is more readable. */
  /* BTN_0 -> A6, BTN_1 -> A7 on 328p.  Must use as analog input and cannot digital read. */
  if(analogRead(BTN_0) >= 128) {   
    bitClear(button_state, 0); 
  } 
  else { 
    bitSet(button_state, 0); 
  }
  if(analogRead(BTN_1) >= 128) {   
    bitClear(button_state, 1); 
  } 
  else { 
    bitSet(button_state, 1); 
  }
  if(digitalRead(BTN_2) == HIGH) { 
    bitClear(button_state, 2); 
  } 
  else { 
    bitSet(button_state, 2); 
  }
  //oled.print(digitalRead(BTN_2));
  if(digitalRead(BTN_3) == HIGH) { 
    bitClear(button_state, 3); 
  } 
  else { 
    bitSet(button_state, 3); 
  }
  //oled.print(digitalRead(BTN_3));
  if(digitalRead(BTN_4) == HIGH) { 
    bitClear(button_state, 4); 
  } 
  else { 
    bitSet(button_state, 4); 
  }
  //oled.print(digitalRead(BTN_4));
  if(digitalRead(BTN_5) == HIGH) { 
    bitClear(button_state, 5); 
  } 
  else { 
    bitSet(button_state, 5); 
  }
  //oled.print(digitalRead(BTN_5));
  bitClear(button_state, 6);
  bitClear(button_state, 7);
  return button_state;
}

