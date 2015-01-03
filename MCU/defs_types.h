#ifndef DEF_TYPES_H
#define DEF_TYPES_H

/* Button Pin Map */
#define BTN_0  A7
#define BTN_1  A6
#define BTN_2  A3
#define BTN_3  A2
#define BTN_4  A1 
#define BTN_5  A0 

/* SSD1306 OLED Pin Map  */
#define OLED_MOSI   9
#define OLED_CLK   10
#define OLED_DC    11
#define OLED_CS    12
#define OLED_RESET 13

#define SERIAL_BUF_SIZE 32

/* SSD1306 OLED Font Type Override */
#define FONT_TYPE 1

/* SSD1306 OLED Constants */
#define CHARS_PER_LINE 21
#define MAX_LINES 8
#define BUTTON_LBL_CHAR_CT 5

/* Display formatting */
#define STATUS_LINE_NUM 0
#define MUTE_CHAR_OFFSET 8
#define PLAYER_CHAR_OFFSET 10
#define BATTERY_CHAR_OFFSET 17

#define BAT_READ_SEC 6000 //60*1000
/* Common States */
typedef enum {MUTE_DISABLED = 0, MUTE_ENABLED = 1} mute_state_t;
typedef enum {BUTTONS_DISABLED = 0, BUTTONS_ENABLED = 1} buttons_state_t;
typedef enum {PLAYER_PLAYING = 0, PLAYER_PAUSED = 1, PLAYER_STOPPED = 2} player_state_t;

// TODO
typedef enum {CMD_SET_LINE = 'l', CMD_SET_MUTE = 'm', CMD_SET_PLAY = 'p', CMD_SET_TIME = 't', CMD_SET_STATE = 's', CMD_GET_BAT='g', CMD_BTN_STATE='c', CMD_BAT_UPDATE='b', CMD_ERROR = 'e'} commands_t;

int freeRam () 
{
  extern int __heap_start, *__brkval; 
  int v; 
  return (int) &v - (__brkval == 0 ? (int) &__heap_start : (int) __brkval); 
}

#endif  /* DEF_TYPES_H */
