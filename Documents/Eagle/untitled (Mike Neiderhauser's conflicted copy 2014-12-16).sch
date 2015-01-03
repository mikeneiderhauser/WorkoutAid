<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE eagle SYSTEM "eagle.dtd">
<eagle version="6.5.0">
<drawing>
<settings>
<setting alwaysvectorfont="no"/>
<setting verticaltext="up"/>
</settings>
<grid distance="0.1" unitdist="inch" unit="inch" style="lines" multiple="1" display="no" altdistance="0.01" altunitdist="inch" altunit="inch"/>
<layers>
<layer number="1" name="Top" color="4" fill="1" visible="no" active="no"/>
<layer number="16" name="Bottom" color="1" fill="1" visible="no" active="no"/>
<layer number="17" name="Pads" color="2" fill="1" visible="no" active="no"/>
<layer number="18" name="Vias" color="2" fill="1" visible="no" active="no"/>
<layer number="19" name="Unrouted" color="6" fill="1" visible="no" active="no"/>
<layer number="20" name="Dimension" color="15" fill="1" visible="no" active="no"/>
<layer number="21" name="tPlace" color="7" fill="1" visible="no" active="no"/>
<layer number="22" name="bPlace" color="7" fill="1" visible="no" active="no"/>
<layer number="23" name="tOrigins" color="15" fill="1" visible="no" active="no"/>
<layer number="24" name="bOrigins" color="15" fill="1" visible="no" active="no"/>
<layer number="25" name="tNames" color="7" fill="1" visible="no" active="no"/>
<layer number="26" name="bNames" color="7" fill="1" visible="no" active="no"/>
<layer number="27" name="tValues" color="7" fill="1" visible="no" active="no"/>
<layer number="28" name="bValues" color="7" fill="1" visible="no" active="no"/>
<layer number="29" name="tStop" color="7" fill="3" visible="no" active="no"/>
<layer number="30" name="bStop" color="7" fill="6" visible="no" active="no"/>
<layer number="31" name="tCream" color="7" fill="4" visible="no" active="no"/>
<layer number="32" name="bCream" color="7" fill="5" visible="no" active="no"/>
<layer number="33" name="tFinish" color="6" fill="3" visible="no" active="no"/>
<layer number="34" name="bFinish" color="6" fill="6" visible="no" active="no"/>
<layer number="35" name="tGlue" color="7" fill="4" visible="no" active="no"/>
<layer number="36" name="bGlue" color="7" fill="5" visible="no" active="no"/>
<layer number="37" name="tTest" color="7" fill="1" visible="no" active="no"/>
<layer number="38" name="bTest" color="7" fill="1" visible="no" active="no"/>
<layer number="39" name="tKeepout" color="4" fill="11" visible="no" active="no"/>
<layer number="40" name="bKeepout" color="1" fill="11" visible="no" active="no"/>
<layer number="41" name="tRestrict" color="4" fill="10" visible="no" active="no"/>
<layer number="42" name="bRestrict" color="1" fill="10" visible="no" active="no"/>
<layer number="43" name="vRestrict" color="2" fill="10" visible="no" active="no"/>
<layer number="44" name="Drills" color="7" fill="1" visible="no" active="no"/>
<layer number="45" name="Holes" color="7" fill="1" visible="no" active="no"/>
<layer number="46" name="Milling" color="3" fill="1" visible="no" active="no"/>
<layer number="47" name="Measures" color="7" fill="1" visible="no" active="no"/>
<layer number="48" name="Document" color="7" fill="1" visible="no" active="no"/>
<layer number="49" name="Reference" color="7" fill="1" visible="no" active="no"/>
<layer number="51" name="tDocu" color="7" fill="1" visible="no" active="no"/>
<layer number="52" name="bDocu" color="7" fill="1" visible="no" active="no"/>
<layer number="91" name="Nets" color="2" fill="1" visible="yes" active="yes"/>
<layer number="92" name="Busses" color="1" fill="1" visible="yes" active="yes"/>
<layer number="93" name="Pins" color="2" fill="1" visible="no" active="yes"/>
<layer number="94" name="Symbols" color="4" fill="1" visible="yes" active="yes"/>
<layer number="95" name="Names" color="7" fill="1" visible="yes" active="yes"/>
<layer number="96" name="Values" color="7" fill="1" visible="yes" active="yes"/>
<layer number="97" name="Info" color="7" fill="1" visible="yes" active="yes"/>
<layer number="98" name="Guide" color="6" fill="1" visible="yes" active="yes"/>
</layers>
<schematic xreflabel="%F%N/%S.%C%R" xrefpart="/%S.%C%R">
<libraries>
<library name="workoutAid">
<packages>
<package name="TRINKET_PRO">
<pad name="9" x="-6.35" y="15.24" drill="0.8" shape="octagon" rot="R270"/>
<pad name="10" x="-6.35" y="12.7" drill="0.8" shape="octagon" rot="R270"/>
<pad name="11" x="-6.35" y="10.16" drill="0.8" shape="octagon" rot="R270"/>
<pad name="12" x="-6.35" y="7.62" drill="0.8" shape="octagon" rot="R270"/>
<pad name="13" x="-6.35" y="5.08" drill="0.8" shape="octagon" rot="R270"/>
<pad name="AR" x="-6.35" y="2.54" drill="0.8" shape="octagon" rot="R270"/>
<pad name="A0" x="-6.35" y="0" drill="0.8" shape="octagon" rot="R270"/>
<pad name="A1" x="-6.35" y="-2.54" drill="0.8" shape="octagon" rot="R270"/>
<pad name="A2" x="-6.35" y="-5.08" drill="0.8" shape="octagon" rot="R270"/>
<pad name="A3" x="-6.35" y="-7.62" drill="0.8" shape="octagon" rot="R270"/>
<pad name="A4" x="-6.35" y="-10.16" drill="0.8" shape="octagon" rot="R270"/>
<pad name="A5" x="-6.35" y="-12.7" drill="0.8" shape="octagon" rot="R270"/>
<pad name="A6" x="-3.81" y="0" drill="0.8" shape="octagon" rot="R270"/>
<pad name="A7" x="-3.81" y="-2.54" drill="0.8" shape="octagon" rot="R270"/>
<pad name="GND@1" x="-6.35" y="-16.51" drill="0.8" shape="octagon" rot="R270"/>
<pad name="CTS" x="-3.81" y="-16.51" drill="0.8" shape="octagon" rot="R270"/>
<pad name="VBUS@1" x="-1.27" y="-16.51" drill="0.8" shape="octagon" rot="R270"/>
<pad name="RX@1" x="1.27" y="-16.51" drill="0.8" shape="octagon" rot="R270"/>
<pad name="TX@1" x="3.81" y="-16.51" drill="0.8" shape="octagon" rot="R270"/>
<pad name="RST@1" x="6.35" y="-16.51" drill="0.8" shape="octagon" rot="R270"/>
<pad name="RST@2" x="6.35" y="-12.7" drill="0.8" shape="octagon" rot="R270"/>
<pad name="RX@2" x="6.35" y="-10.16" drill="0.8" shape="octagon" rot="R270"/>
<pad name="TX@2" x="6.35" y="-7.62" drill="0.8" shape="octagon" rot="R270"/>
<pad name="3" x="6.35" y="-5.08" drill="0.8" shape="octagon" rot="R270"/>
<pad name="4" x="6.35" y="-2.54" drill="0.8" shape="octagon" rot="R270"/>
<pad name="5" x="6.35" y="0" drill="0.8" shape="octagon" rot="R270"/>
<pad name="6" x="6.35" y="2.54" drill="0.8" shape="octagon" rot="R270"/>
<pad name="8" x="6.35" y="5.08" drill="0.8" shape="octagon" rot="R270"/>
<pad name="V" x="6.35" y="7.62" drill="0.8" shape="octagon" rot="R270"/>
<pad name="VBUS@2" x="6.35" y="10.16" drill="0.8" shape="octagon" rot="R270"/>
<pad name="GND@2" x="6.35" y="12.7" drill="0.8" shape="octagon" rot="R270"/>
<pad name="VBAT" x="6.35" y="15.24" drill="0.8" shape="octagon" rot="R270"/>
</package>
<package name="SSD1306">
<pad name="RST" x="-1.27" y="2.54" drill="0.8"/>
<pad name="CS" x="1.27" y="2.54" drill="0.8"/>
<pad name="DC" x="-3.81" y="2.54" drill="0.8"/>
<pad name="3.3V" x="3.81" y="2.54" drill="0.8"/>
<pad name="CLK" x="-6.35" y="2.54" drill="0.8" rot="R90"/>
<pad name="VIN" x="6.35" y="2.54" drill="0.8"/>
<pad name="DATA" x="-8.89" y="2.54" drill="0.8"/>
<pad name="GND" x="8.89" y="2.54" drill="0.8"/>
</package>
<package name="TRINKET_PRO_LIPO_BACKPACK">
<pad name="VBAT@1" x="-2.54" y="-1.27" drill="0.8" shape="octagon"/>
<pad name="GND@1" x="-2.54" y="1.27" drill="0.8" shape="octagon"/>
<pad name="VBUS" x="-2.54" y="3.81" drill="0.8" shape="octagon"/>
<pad name="SW0" x="-1.27" y="6.35" drill="0.8" shape="octagon"/>
<pad name="SW1" x="1.27" y="6.35" drill="0.8" shape="octagon"/>
<pad name="GND@2" x="5.08" y="6.35" drill="0.8" shape="octagon"/>
<pad name="VBAT@2" x="7.62" y="6.35" drill="0.8" shape="octagon"/>
</package>
<package name="L4931">
<pad name="GND" x="0" y="0" drill="0.8" rot="R180"/>
<pad name="VIN" x="-2.54" y="0" drill="0.8"/>
<pad name="VOUT" x="2.54" y="0" drill="0.8" rot="R90"/>
</package>
<package name="VCC">
<pad name="VCC" x="0" y="0" drill="0.8" shape="square"/>
</package>
<package name="GND">
<pad name="GND" x="-5.08" y="2.54" drill="0.8" shape="square"/>
</package>
<package name="MC14490P">
<pad name="GND" x="-3.81" y="-5.08" drill="0.8" shape="square"/>
<pad name="OSC_IN" x="-3.81" y="-2.54" drill="0.8"/>
<pad name="F_OUT" x="-3.81" y="0" drill="0.8"/>
<pad name="E_IN" x="-3.81" y="2.54" drill="0.8"/>
<pad name="D_OUT" x="-3.81" y="5.08" drill="0.8"/>
<pad name="C_IN" x="-3.81" y="7.62" drill="0.8"/>
<pad name="B_OUT" x="-3.81" y="10.16" drill="0.8"/>
<pad name="A_IN" x="-3.81" y="12.7" drill="0.8"/>
<pad name="V" x="3.81" y="12.7" drill="0.8"/>
<pad name="A_OUT" x="3.81" y="10.16" drill="0.8"/>
<pad name="B_IN" x="3.81" y="7.62" drill="0.8"/>
<pad name="C_OUT" x="3.81" y="5.08" drill="0.8"/>
<pad name="D_IN" x="3.81" y="2.54" drill="0.8"/>
<pad name="E_OUT" x="3.81" y="0" drill="0.8"/>
<pad name="F_IN" x="3.81" y="-2.54" drill="0.8"/>
<pad name="OSC_OUT" x="3.81" y="-5.08" drill="0.8"/>
</package>
<package name="SW">
<pad name="SW_IN" x="-1.27" y="0" drill="0.8" shape="square"/>
<pad name="SW_OUT" x="1.27" y="0" drill="0.8"/>
</package>
</packages>
<symbols>
<symbol name="TRINKET_PRO">
<wire x1="-7.62" y1="22.86" x2="-7.62" y2="-25.4" width="0.254" layer="94"/>
<wire x1="-7.62" y1="-25.4" x2="10.16" y2="-25.4" width="0.254" layer="94"/>
<wire x1="10.16" y1="-25.4" x2="10.16" y2="22.86" width="0.254" layer="94"/>
<wire x1="10.16" y1="22.86" x2="-7.62" y2="22.86" width="0.254" layer="94"/>
<pin name="9" x="-12.7" y="20.32" length="middle"/>
<pin name="10" x="-12.7" y="17.78" length="middle"/>
<pin name="11" x="-12.7" y="15.24" length="middle"/>
<pin name="12" x="-12.7" y="12.7" length="middle"/>
<pin name="13" x="-12.7" y="10.16" length="middle"/>
<pin name="AR" x="-12.7" y="7.62" length="middle"/>
<pin name="A0" x="-12.7" y="5.08" length="middle"/>
<pin name="A1" x="-12.7" y="2.54" length="middle"/>
<pin name="A2" x="-12.7" y="0" length="middle"/>
<pin name="A3" x="-12.7" y="-2.54" length="middle"/>
<pin name="A4" x="-12.7" y="-5.08" length="middle"/>
<pin name="A5" x="-12.7" y="-7.62" length="middle"/>
<pin name="A6" x="-12.7" y="-10.16" length="middle"/>
<pin name="A7" x="-12.7" y="-12.7" length="middle"/>
<pin name="VBAT" x="15.24" y="20.32" length="middle" rot="R180"/>
<pin name="GND@2" x="15.24" y="17.78" length="middle" rot="R180"/>
<pin name="VBUS@2" x="15.24" y="15.24" length="middle" rot="R180"/>
<pin name="V" x="15.24" y="12.7" length="middle" rot="R180"/>
<pin name="8" x="15.24" y="10.16" length="middle" rot="R180"/>
<pin name="6" x="15.24" y="7.62" length="middle" rot="R180"/>
<pin name="5" x="15.24" y="5.08" length="middle" rot="R180"/>
<pin name="4" x="15.24" y="2.54" length="middle" rot="R180"/>
<pin name="3" x="15.24" y="0" length="middle" rot="R180"/>
<pin name="TX@2" x="15.24" y="-2.54" length="middle" rot="R180"/>
<pin name="RX@2" x="15.24" y="-5.08" length="middle" rot="R180"/>
<pin name="RST@2" x="15.24" y="-7.62" length="middle" rot="R180"/>
<pin name="GND@1" x="-5.08" y="-30.48" length="middle" rot="R90"/>
<pin name="CTS" x="-2.54" y="-30.48" length="middle" rot="R90"/>
<pin name="VBUS@1" x="0" y="-30.48" length="middle" rot="R90"/>
<pin name="RX@1" x="2.54" y="-30.48" length="middle" rot="R90"/>
<pin name="TX@1" x="5.08" y="-30.48" length="middle" rot="R90"/>
<pin name="RST@1" x="7.62" y="-30.48" length="middle" rot="R90"/>
<text x="0" y="0" size="1.778" layer="94" rot="R90">Trinket Pro</text>
<text x="-7.62" y="22.86" size="1.778" layer="95">&gt;NAME</text>
</symbol>
<symbol name="SSD1306">
<wire x1="-12.7" y1="5.08" x2="15.24" y2="5.08" width="0.254" layer="94"/>
<wire x1="15.24" y1="5.08" x2="15.24" y2="-5.08" width="0.254" layer="94"/>
<wire x1="15.24" y1="-5.08" x2="-12.7" y2="-5.08" width="0.254" layer="94"/>
<wire x1="-12.7" y1="-5.08" x2="-12.7" y2="5.08" width="0.254" layer="94"/>
<pin name="DATA" x="-7.62" y="10.16" length="middle" rot="R270"/>
<pin name="CLK" x="-5.08" y="10.16" length="middle" rot="R270"/>
<pin name="DC" x="-2.54" y="10.16" length="middle" rot="R270"/>
<pin name="RST" x="0" y="10.16" length="middle" rot="R270"/>
<pin name="CS" x="2.54" y="10.16" length="middle" rot="R270"/>
<pin name="3.3V" x="5.08" y="10.16" length="middle" rot="R270"/>
<pin name="VIN" x="7.62" y="10.16" length="middle" rot="R270"/>
<pin name="GND" x="10.16" y="10.16" length="middle" rot="R270"/>
<text x="-5.08" y="-5.08" size="1.778" layer="94">SSD1306</text>
</symbol>
<symbol name="TRINKET_PRO_LIPO_BACKPACK">
<pin name="VBAT@1" x="15.24" y="5.08" length="middle" rot="R180"/>
<pin name="GND@1" x="15.24" y="2.54" length="middle" rot="R180"/>
<pin name="VBUS" x="15.24" y="0" length="middle" rot="R180"/>
<pin name="SW0" x="7.62" y="-15.24" length="middle" rot="R90"/>
<pin name="SW1" x="5.08" y="-15.24" length="middle" rot="R90"/>
<pin name="GND@2" x="-2.54" y="-15.24" length="middle" rot="R90"/>
<pin name="VBAT@2" x="-5.08" y="-15.24" length="middle" rot="R90"/>
<wire x1="-12.7" y1="7.62" x2="-12.7" y2="-10.16" width="0.254" layer="94"/>
<wire x1="-12.7" y1="-10.16" x2="10.16" y2="-10.16" width="0.254" layer="94"/>
<wire x1="10.16" y1="-10.16" x2="10.16" y2="7.62" width="0.254" layer="94"/>
<wire x1="10.16" y1="7.62" x2="-12.7" y2="7.62" width="0.254" layer="94"/>
<text x="-7.62" y="-7.62" size="1.27" layer="94" rot="R90">Trinket Pro 
LiPo Backpack</text>
</symbol>
<symbol name="L4931">
<pin name="VIN" x="-5.08" y="-5.08" length="middle" rot="R90"/>
<pin name="GND" x="0" y="-5.08" length="middle" rot="R90"/>
<pin name="VOUT" x="5.08" y="-5.08" length="middle" rot="R90"/>
<wire x1="-7.62" y1="0" x2="7.62" y2="0" width="0.254" layer="94"/>
<wire x1="7.62" y1="0" x2="7.62" y2="12.7" width="0.254" layer="94"/>
<wire x1="7.62" y1="12.7" x2="-7.62" y2="12.7" width="0.254" layer="94"/>
<wire x1="-7.62" y1="12.7" x2="-7.62" y2="0" width="0.254" layer="94"/>
<text x="-5.08" y="10.16" size="1.778" layer="94">L4931</text>
</symbol>
<symbol name="VCC">
<wire x1="0" y1="5.08" x2="-5.08" y2="0" width="0.254" layer="94"/>
<wire x1="-5.08" y1="0" x2="0" y2="0" width="0.254" layer="94"/>
<wire x1="0" y1="0" x2="5.08" y2="0" width="0.254" layer="94"/>
<wire x1="5.08" y1="0" x2="0" y2="5.08" width="0.254" layer="94"/>
<wire x1="0" y1="0" x2="0" y2="-2.54" width="0.254" layer="94"/>
<pin name="VCC" x="0" y="-7.62" length="middle" rot="R90"/>
</symbol>
<symbol name="GND">
<wire x1="-2.54" y1="0" x2="2.54" y2="0" width="0.254" layer="94"/>
<wire x1="2.54" y1="0" x2="10.16" y2="0" width="0.254" layer="94"/>
<wire x1="0" y1="-2.54" x2="7.62" y2="-2.54" width="0.254" layer="94"/>
<wire x1="2.54" y1="-5.08" x2="5.08" y2="-5.08" width="0.254" layer="94"/>
<wire x1="2.54" y1="0" x2="2.54" y2="2.54" width="0.254" layer="94"/>
<pin name="GND" x="2.54" y="7.62" length="middle" rot="R270"/>
</symbol>
<symbol name="MC14490P">
<wire x1="-10.16" y1="12.7" x2="10.16" y2="12.7" width="0.254" layer="94"/>
<pin name="A_IN" x="-15.24" y="5.08" length="middle"/>
<pin name="B_OUT" x="-15.24" y="2.54" length="middle"/>
<pin name="C_IN" x="-15.24" y="0" length="middle"/>
<pin name="D_OUT" x="-15.24" y="-2.54" length="middle"/>
<pin name="E_IN" x="-15.24" y="-5.08" length="middle"/>
<pin name="F_OUT" x="-15.24" y="-7.62" length="middle"/>
<pin name="OSC_IN" x="-15.24" y="-10.16" length="middle"/>
<pin name="GND" x="-15.24" y="-12.7" length="middle"/>
<pin name="V" x="15.24" y="5.08" length="middle" rot="R180"/>
<pin name="A_OUT" x="15.24" y="2.54" length="middle" rot="R180"/>
<pin name="B_IN" x="15.24" y="0" length="middle" rot="R180"/>
<pin name="C_OUT" x="15.24" y="-2.54" length="middle" rot="R180"/>
<pin name="D_IN" x="15.24" y="-5.08" length="middle" rot="R180"/>
<pin name="E_OUT" x="15.24" y="-7.62" length="middle" rot="R180"/>
<pin name="F_IN" x="15.24" y="-10.16" length="middle" rot="R180"/>
<pin name="OSC_OUT" x="15.24" y="-12.7" length="middle" rot="R180"/>
<wire x1="-10.16" y1="12.7" x2="-10.16" y2="-15.24" width="0.254" layer="94"/>
<wire x1="-10.16" y1="-15.24" x2="10.16" y2="-15.24" width="0.254" layer="94"/>
<wire x1="10.16" y1="-15.24" x2="10.16" y2="12.7" width="0.254" layer="94"/>
<text x="-5.08" y="10.16" size="1.778" layer="94">MC14490P</text>
</symbol>
<symbol name="SW">
<wire x1="-5.08" y1="0" x2="-2.54" y2="0" width="0.254" layer="94"/>
<wire x1="-2.54" y1="0" x2="0" y2="2.54" width="0.254" layer="94"/>
<wire x1="0" y1="0" x2="2.54" y2="0" width="0.254" layer="94"/>
<pin name="SW_IN" x="-7.62" y="0" length="middle"/>
<pin name="SW_OUT" x="5.08" y="0" length="middle" rot="R180"/>
</symbol>
</symbols>
<devicesets>
<deviceset name="TRINKET_PRO">
<gates>
<gate name="G$1" symbol="TRINKET_PRO" x="0" y="2.54"/>
</gates>
<devices>
<device name="" package="TRINKET_PRO">
<connects>
<connect gate="G$1" pin="10" pad="10"/>
<connect gate="G$1" pin="11" pad="11"/>
<connect gate="G$1" pin="12" pad="12"/>
<connect gate="G$1" pin="13" pad="13"/>
<connect gate="G$1" pin="3" pad="3"/>
<connect gate="G$1" pin="4" pad="4"/>
<connect gate="G$1" pin="5" pad="5"/>
<connect gate="G$1" pin="6" pad="6"/>
<connect gate="G$1" pin="8" pad="8"/>
<connect gate="G$1" pin="9" pad="9"/>
<connect gate="G$1" pin="A0" pad="A0"/>
<connect gate="G$1" pin="A1" pad="A1"/>
<connect gate="G$1" pin="A2" pad="A2"/>
<connect gate="G$1" pin="A3" pad="A3"/>
<connect gate="G$1" pin="A4" pad="A4"/>
<connect gate="G$1" pin="A5" pad="A5"/>
<connect gate="G$1" pin="A6" pad="A6"/>
<connect gate="G$1" pin="A7" pad="A7"/>
<connect gate="G$1" pin="AR" pad="AR"/>
<connect gate="G$1" pin="CTS" pad="CTS"/>
<connect gate="G$1" pin="GND@1" pad="GND@1"/>
<connect gate="G$1" pin="GND@2" pad="GND@2"/>
<connect gate="G$1" pin="RST@1" pad="RST@1"/>
<connect gate="G$1" pin="RST@2" pad="RST@2"/>
<connect gate="G$1" pin="RX@1" pad="RX@1"/>
<connect gate="G$1" pin="RX@2" pad="RX@2"/>
<connect gate="G$1" pin="TX@1" pad="TX@1"/>
<connect gate="G$1" pin="TX@2" pad="TX@2"/>
<connect gate="G$1" pin="V" pad="V"/>
<connect gate="G$1" pin="VBAT" pad="VBAT"/>
<connect gate="G$1" pin="VBUS@1" pad="VBUS@1"/>
<connect gate="G$1" pin="VBUS@2" pad="VBUS@2"/>
</connects>
<technologies>
<technology name=""/>
</technologies>
</device>
</devices>
</deviceset>
<deviceset name="SSD1306">
<gates>
<gate name="G$1" symbol="SSD1306" x="0" y="0"/>
</gates>
<devices>
<device name="" package="SSD1306">
<connects>
<connect gate="G$1" pin="3.3V" pad="3.3V"/>
<connect gate="G$1" pin="CLK" pad="CLK"/>
<connect gate="G$1" pin="CS" pad="CS"/>
<connect gate="G$1" pin="DATA" pad="DATA"/>
<connect gate="G$1" pin="DC" pad="DC"/>
<connect gate="G$1" pin="GND" pad="GND"/>
<connect gate="G$1" pin="RST" pad="RST"/>
<connect gate="G$1" pin="VIN" pad="VIN"/>
</connects>
<technologies>
<technology name=""/>
</technologies>
</device>
</devices>
</deviceset>
<deviceset name="TRINKET_PRO_LIPO_BACKPACK">
<gates>
<gate name="G$1" symbol="TRINKET_PRO_LIPO_BACKPACK" x="0" y="2.54"/>
</gates>
<devices>
<device name="" package="TRINKET_PRO_LIPO_BACKPACK">
<connects>
<connect gate="G$1" pin="GND@1" pad="GND@1"/>
<connect gate="G$1" pin="GND@2" pad="GND@2"/>
<connect gate="G$1" pin="SW0" pad="SW0"/>
<connect gate="G$1" pin="SW1" pad="SW1"/>
<connect gate="G$1" pin="VBAT@1" pad="VBAT@1"/>
<connect gate="G$1" pin="VBAT@2" pad="VBAT@2"/>
<connect gate="G$1" pin="VBUS" pad="VBUS"/>
</connects>
<technologies>
<technology name=""/>
</technologies>
</device>
</devices>
</deviceset>
<deviceset name="L4931">
<gates>
<gate name="G$1" symbol="L4931" x="0" y="-5.08"/>
</gates>
<devices>
<device name="" package="L4931">
<connects>
<connect gate="G$1" pin="GND" pad="GND"/>
<connect gate="G$1" pin="VIN" pad="VIN"/>
<connect gate="G$1" pin="VOUT" pad="VOUT"/>
</connects>
<technologies>
<technology name=""/>
</technologies>
</device>
</devices>
</deviceset>
<deviceset name="VCC">
<gates>
<gate name="G$1" symbol="VCC" x="0" y="7.62"/>
</gates>
<devices>
<device name="" package="VCC">
<connects>
<connect gate="G$1" pin="VCC" pad="VCC"/>
</connects>
<technologies>
<technology name=""/>
</technologies>
</device>
</devices>
</deviceset>
<deviceset name="GND">
<gates>
<gate name="G$1" symbol="GND" x="-2.54" y="-7.62"/>
</gates>
<devices>
<device name="" package="GND">
<connects>
<connect gate="G$1" pin="GND" pad="GND"/>
</connects>
<technologies>
<technology name=""/>
</technologies>
</device>
</devices>
</deviceset>
<deviceset name="MC14490P">
<gates>
<gate name="G$1" symbol="MC14490P" x="0" y="0"/>
</gates>
<devices>
<device name="" package="MC14490P">
<connects>
<connect gate="G$1" pin="A_IN" pad="A_IN"/>
<connect gate="G$1" pin="A_OUT" pad="A_OUT"/>
<connect gate="G$1" pin="B_IN" pad="B_IN"/>
<connect gate="G$1" pin="B_OUT" pad="B_OUT"/>
<connect gate="G$1" pin="C_IN" pad="C_IN"/>
<connect gate="G$1" pin="C_OUT" pad="C_OUT"/>
<connect gate="G$1" pin="D_IN" pad="D_IN"/>
<connect gate="G$1" pin="D_OUT" pad="D_OUT"/>
<connect gate="G$1" pin="E_IN" pad="E_IN"/>
<connect gate="G$1" pin="E_OUT" pad="E_OUT"/>
<connect gate="G$1" pin="F_IN" pad="F_IN"/>
<connect gate="G$1" pin="F_OUT" pad="F_OUT"/>
<connect gate="G$1" pin="GND" pad="GND"/>
<connect gate="G$1" pin="OSC_IN" pad="OSC_IN"/>
<connect gate="G$1" pin="OSC_OUT" pad="OSC_OUT"/>
<connect gate="G$1" pin="V" pad="V"/>
</connects>
<technologies>
<technology name=""/>
</technologies>
</device>
</devices>
</deviceset>
<deviceset name="SW">
<gates>
<gate name="G$1" symbol="SW" x="0" y="2.54"/>
</gates>
<devices>
<device name="" package="SW">
<connects>
<connect gate="G$1" pin="SW_IN" pad="SW_IN"/>
<connect gate="G$1" pin="SW_OUT" pad="SW_OUT"/>
</connects>
<technologies>
<technology name=""/>
</technologies>
</device>
</devices>
</deviceset>
</devicesets>
</library>
</libraries>
<attributes>
</attributes>
<variantdefs>
</variantdefs>
<classes>
<class number="0" name="default" width="0" drill="0">
</class>
</classes>
<parts>
<part name="U$1" library="workoutAid" deviceset="TRINKET_PRO" device=""/>
<part name="U$2" library="workoutAid" deviceset="SSD1306" device=""/>
<part name="U$3" library="workoutAid" deviceset="TRINKET_PRO_LIPO_BACKPACK" device=""/>
<part name="U$4" library="workoutAid" deviceset="L4931" device=""/>
<part name="U$5" library="workoutAid" deviceset="VCC" device=""/>
<part name="U$6" library="workoutAid" deviceset="VCC" device=""/>
<part name="U$7" library="workoutAid" deviceset="GND" device=""/>
<part name="U$8" library="workoutAid" deviceset="GND" device=""/>
<part name="U$9" library="workoutAid" deviceset="MC14490P" device=""/>
<part name="U$10" library="workoutAid" deviceset="VCC" device=""/>
<part name="U$11" library="workoutAid" deviceset="GND" device=""/>
<part name="U$12" library="workoutAid" deviceset="SW" device=""/>
<part name="U$13" library="workoutAid" deviceset="SW" device=""/>
<part name="U$14" library="workoutAid" deviceset="SW" device=""/>
<part name="U$15" library="workoutAid" deviceset="SW" device=""/>
<part name="U$16" library="workoutAid" deviceset="SW" device=""/>
<part name="U$17" library="workoutAid" deviceset="SW" device=""/>
<part name="U$18" library="workoutAid" deviceset="GND" device=""/>
<part name="U$19" library="workoutAid" deviceset="SW" device=""/>
</parts>
<sheets>
<sheet>
<plain>
</plain>
<instances>
<instance part="U$1" gate="G$1" x="43.18" y="55.88"/>
<instance part="U$2" gate="G$1" x="-12.7" y="68.58" rot="R270"/>
<instance part="U$3" gate="G$1" x="45.72" y="104.14"/>
<instance part="U$4" gate="G$1" x="88.9" y="81.28"/>
<instance part="U$5" gate="G$1" x="104.14" y="88.9"/>
<instance part="U$6" gate="G$1" x="2.54" y="88.9"/>
<instance part="U$7" gate="G$1" x="-5.08" y="48.26"/>
<instance part="U$8" gate="G$1" x="86.36" y="60.96"/>
<instance part="U$9" gate="G$1" x="45.72" y="2.54"/>
<instance part="U$10" gate="G$1" x="63.5" y="17.78"/>
<instance part="U$11" gate="G$1" x="25.4" y="-25.4"/>
<instance part="U$12" gate="G$1" x="-20.32" y="25.4"/>
<instance part="U$13" gate="G$1" x="-20.32" y="20.32" smashed="yes"/>
<instance part="U$14" gate="G$1" x="-20.32" y="15.24" smashed="yes"/>
<instance part="U$15" gate="G$1" x="-20.32" y="10.16" smashed="yes"/>
<instance part="U$16" gate="G$1" x="-20.32" y="5.08" smashed="yes"/>
<instance part="U$17" gate="G$1" x="-20.32" y="0" smashed="yes"/>
<instance part="U$18" gate="G$1" x="-38.1" y="-12.7"/>
<instance part="U$19" gate="G$1" x="93.98" y="109.22"/>
</instances>
<busses>
</busses>
<nets>
<net name="OLED_DATA" class="0">
<segment>
<pinref part="U$2" gate="G$1" pin="DATA"/>
<pinref part="U$1" gate="G$1" pin="9"/>
<wire x1="-2.54" y1="76.2" x2="30.48" y2="76.2" width="0.1524" layer="91"/>
<label x="5.08" y="76.2" size="1.778" layer="95"/>
</segment>
</net>
<net name="OLED_CLK" class="0">
<segment>
<pinref part="U$2" gate="G$1" pin="CLK"/>
<pinref part="U$1" gate="G$1" pin="10"/>
<wire x1="-2.54" y1="73.66" x2="30.48" y2="73.66" width="0.1524" layer="91"/>
<label x="5.08" y="73.66" size="1.778" layer="95"/>
</segment>
</net>
<net name="OLED_DC" class="0">
<segment>
<pinref part="U$2" gate="G$1" pin="DC"/>
<pinref part="U$1" gate="G$1" pin="11"/>
<wire x1="-2.54" y1="71.12" x2="30.48" y2="71.12" width="0.1524" layer="91"/>
<label x="5.08" y="71.12" size="1.778" layer="95"/>
</segment>
</net>
<net name="OLED_RST" class="0">
<segment>
<pinref part="U$2" gate="G$1" pin="RST"/>
<wire x1="-2.54" y1="68.58" x2="22.86" y2="68.58" width="0.1524" layer="91"/>
<wire x1="22.86" y1="68.58" x2="22.86" y2="66.04" width="0.1524" layer="91"/>
<pinref part="U$1" gate="G$1" pin="13"/>
<wire x1="22.86" y1="66.04" x2="30.48" y2="66.04" width="0.1524" layer="91"/>
<label x="5.08" y="68.58" size="1.778" layer="95"/>
</segment>
</net>
<net name="OLED_CS" class="0">
<segment>
<pinref part="U$1" gate="G$1" pin="12"/>
<wire x1="30.48" y1="68.58" x2="25.4" y2="68.58" width="0.1524" layer="91"/>
<wire x1="25.4" y1="68.58" x2="25.4" y2="63.5" width="0.1524" layer="91"/>
<wire x1="25.4" y1="63.5" x2="20.32" y2="63.5" width="0.1524" layer="91"/>
<wire x1="20.32" y1="63.5" x2="20.32" y2="66.04" width="0.1524" layer="91"/>
<pinref part="U$2" gate="G$1" pin="CS"/>
<wire x1="20.32" y1="66.04" x2="-2.54" y2="66.04" width="0.1524" layer="91"/>
<label x="5.08" y="66.04" size="1.778" layer="95"/>
</segment>
</net>
<net name="VBAT" class="0">
<segment>
<pinref part="U$1" gate="G$1" pin="VBAT"/>
<wire x1="58.42" y1="76.2" x2="73.66" y2="76.2" width="0.1524" layer="91"/>
<wire x1="73.66" y1="76.2" x2="73.66" y2="109.22" width="0.1524" layer="91"/>
<pinref part="U$3" gate="G$1" pin="VBAT@1"/>
<wire x1="73.66" y1="109.22" x2="60.96" y2="109.22" width="0.1524" layer="91"/>
<pinref part="U$4" gate="G$1" pin="VIN"/>
<wire x1="83.82" y1="76.2" x2="73.66" y2="76.2" width="0.1524" layer="91"/>
</segment>
</net>
<net name="GND" class="0">
<segment>
<pinref part="U$1" gate="G$1" pin="GND@2"/>
<wire x1="58.42" y1="73.66" x2="71.12" y2="73.66" width="0.1524" layer="91"/>
<wire x1="71.12" y1="73.66" x2="71.12" y2="81.28" width="0.1524" layer="91"/>
<pinref part="U$3" gate="G$1" pin="GND@1"/>
<wire x1="71.12" y1="81.28" x2="71.12" y2="106.68" width="0.1524" layer="91"/>
<wire x1="71.12" y1="106.68" x2="60.96" y2="106.68" width="0.1524" layer="91"/>
<pinref part="U$3" gate="G$1" pin="GND@2"/>
<wire x1="43.18" y1="88.9" x2="43.18" y2="81.28" width="0.1524" layer="91"/>
<wire x1="43.18" y1="81.28" x2="71.12" y2="81.28" width="0.1524" layer="91"/>
<junction x="71.12" y="81.28"/>
<pinref part="U$4" gate="G$1" pin="GND"/>
<wire x1="88.9" y1="76.2" x2="88.9" y2="73.66" width="0.1524" layer="91"/>
<pinref part="U$8" gate="G$1" pin="GND"/>
<wire x1="88.9" y1="73.66" x2="88.9" y2="68.58" width="0.1524" layer="91"/>
<wire x1="71.12" y1="73.66" x2="88.9" y2="73.66" width="0.1524" layer="91"/>
</segment>
<segment>
<pinref part="U$2" gate="G$1" pin="GND"/>
<wire x1="-2.54" y1="58.42" x2="-2.54" y2="55.88" width="0.1524" layer="91"/>
<pinref part="U$7" gate="G$1" pin="GND"/>
</segment>
<segment>
<pinref part="U$11" gate="G$1" pin="GND"/>
<pinref part="U$9" gate="G$1" pin="GND"/>
<wire x1="27.94" y1="-17.78" x2="27.94" y2="-10.16" width="0.1524" layer="91"/>
<wire x1="27.94" y1="-10.16" x2="30.48" y2="-10.16" width="0.1524" layer="91"/>
</segment>
<segment>
<pinref part="U$12" gate="G$1" pin="SW_IN"/>
<wire x1="-27.94" y1="25.4" x2="-35.56" y2="25.4" width="0.1524" layer="91"/>
<wire x1="-35.56" y1="25.4" x2="-35.56" y2="20.32" width="0.1524" layer="91"/>
<pinref part="U$17" gate="G$1" pin="SW_IN"/>
<wire x1="-35.56" y1="20.32" x2="-35.56" y2="15.24" width="0.1524" layer="91"/>
<wire x1="-35.56" y1="15.24" x2="-35.56" y2="10.16" width="0.1524" layer="91"/>
<wire x1="-35.56" y1="10.16" x2="-35.56" y2="5.08" width="0.1524" layer="91"/>
<wire x1="-35.56" y1="5.08" x2="-35.56" y2="0" width="0.1524" layer="91"/>
<wire x1="-35.56" y1="0" x2="-35.56" y2="-5.08" width="0.1524" layer="91"/>
<wire x1="-27.94" y1="0" x2="-35.56" y2="0" width="0.1524" layer="91"/>
<pinref part="U$16" gate="G$1" pin="SW_IN"/>
<wire x1="-27.94" y1="5.08" x2="-35.56" y2="5.08" width="0.1524" layer="91"/>
<pinref part="U$15" gate="G$1" pin="SW_IN"/>
<wire x1="-27.94" y1="10.16" x2="-35.56" y2="10.16" width="0.1524" layer="91"/>
<pinref part="U$14" gate="G$1" pin="SW_IN"/>
<wire x1="-27.94" y1="15.24" x2="-35.56" y2="15.24" width="0.1524" layer="91"/>
<pinref part="U$13" gate="G$1" pin="SW_IN"/>
<wire x1="-27.94" y1="20.32" x2="-35.56" y2="20.32" width="0.1524" layer="91"/>
</segment>
</net>
<net name="VBUS" class="0">
<segment>
<pinref part="U$3" gate="G$1" pin="VBUS"/>
<wire x1="60.96" y1="104.14" x2="68.58" y2="104.14" width="0.1524" layer="91"/>
<wire x1="68.58" y1="104.14" x2="68.58" y2="71.12" width="0.1524" layer="91"/>
<pinref part="U$1" gate="G$1" pin="VBUS@2"/>
<wire x1="68.58" y1="71.12" x2="58.42" y2="71.12" width="0.1524" layer="91"/>
</segment>
</net>
<net name="VCC" class="0">
<segment>
<pinref part="U$4" gate="G$1" pin="VOUT"/>
<wire x1="93.98" y1="76.2" x2="104.14" y2="76.2" width="0.1524" layer="91"/>
<wire x1="104.14" y1="76.2" x2="104.14" y2="81.28" width="0.1524" layer="91"/>
<pinref part="U$5" gate="G$1" pin="VCC"/>
</segment>
<segment>
<pinref part="U$10" gate="G$1" pin="VCC"/>
<pinref part="U$9" gate="G$1" pin="V"/>
<wire x1="63.5" y1="10.16" x2="63.5" y2="7.62" width="0.1524" layer="91"/>
<wire x1="63.5" y1="7.62" x2="60.96" y2="7.62" width="0.1524" layer="91"/>
</segment>
<segment>
<pinref part="U$2" gate="G$1" pin="3.3V"/>
<wire x1="-2.54" y1="63.5" x2="2.54" y2="63.5" width="0.1524" layer="91"/>
<pinref part="U$6" gate="G$1" pin="VCC"/>
<wire x1="2.54" y1="63.5" x2="2.54" y2="81.28" width="0.1524" layer="91"/>
</segment>
</net>
<net name="BUT_0" class="0">
<segment>
<pinref part="U$1" gate="G$1" pin="A0"/>
<wire x1="30.48" y1="60.96" x2="20.32" y2="60.96" width="0.1524" layer="91"/>
<label x="20.32" y="60.96" size="1.778" layer="95"/>
</segment>
<segment>
<pinref part="U$9" gate="G$1" pin="A_OUT"/>
<wire x1="60.96" y1="5.08" x2="71.12" y2="5.08" width="0.1524" layer="91"/>
<label x="66.04" y="5.08" size="1.778" layer="95"/>
</segment>
</net>
<net name="BUT_1" class="0">
<segment>
<pinref part="U$1" gate="G$1" pin="A1"/>
<wire x1="30.48" y1="58.42" x2="20.32" y2="58.42" width="0.1524" layer="91"/>
<label x="20.32" y="58.42" size="1.778" layer="95"/>
</segment>
<segment>
<pinref part="U$9" gate="G$1" pin="B_OUT"/>
<wire x1="30.48" y1="5.08" x2="20.32" y2="5.08" width="0.1524" layer="91"/>
<label x="15.24" y="5.08" size="1.778" layer="95"/>
</segment>
</net>
<net name="BUT_2" class="0">
<segment>
<pinref part="U$1" gate="G$1" pin="A2"/>
<wire x1="30.48" y1="55.88" x2="20.32" y2="55.88" width="0.1524" layer="91"/>
<label x="20.32" y="55.88" size="1.778" layer="95"/>
</segment>
<segment>
<pinref part="U$9" gate="G$1" pin="C_OUT"/>
<wire x1="60.96" y1="0" x2="71.12" y2="0" width="0.1524" layer="91"/>
<label x="66.04" y="0" size="1.778" layer="95"/>
</segment>
</net>
<net name="SDA" class="0">
<segment>
<pinref part="U$1" gate="G$1" pin="A4"/>
<wire x1="30.48" y1="50.8" x2="20.32" y2="50.8" width="0.1524" layer="91"/>
<label x="20.32" y="50.8" size="1.778" layer="95"/>
</segment>
</net>
<net name="BUT_3" class="0">
<segment>
<pinref part="U$1" gate="G$1" pin="A3"/>
<wire x1="30.48" y1="53.34" x2="20.32" y2="53.34" width="0.1524" layer="91"/>
<label x="20.32" y="53.34" size="1.778" layer="95"/>
</segment>
<segment>
<pinref part="U$9" gate="G$1" pin="D_OUT"/>
<wire x1="30.48" y1="0" x2="20.32" y2="0" width="0.1524" layer="91"/>
<label x="15.24" y="0" size="1.778" layer="95"/>
</segment>
</net>
<net name="BUT_4" class="0">
<segment>
<pinref part="U$1" gate="G$1" pin="A6"/>
<wire x1="30.48" y1="45.72" x2="20.32" y2="45.72" width="0.1524" layer="91"/>
<label x="20.32" y="45.72" size="1.778" layer="95"/>
</segment>
<segment>
<pinref part="U$9" gate="G$1" pin="E_OUT"/>
<wire x1="60.96" y1="-5.08" x2="71.12" y2="-5.08" width="0.1524" layer="91"/>
<label x="66.04" y="-5.08" size="1.778" layer="95"/>
</segment>
</net>
<net name="BUT_5" class="0">
<segment>
<pinref part="U$1" gate="G$1" pin="A7"/>
<wire x1="30.48" y1="43.18" x2="20.32" y2="43.18" width="0.1524" layer="91"/>
<label x="20.32" y="43.18" size="1.778" layer="95"/>
</segment>
<segment>
<pinref part="U$9" gate="G$1" pin="F_OUT"/>
<wire x1="30.48" y1="-5.08" x2="20.32" y2="-5.08" width="0.1524" layer="91"/>
<label x="15.24" y="-5.08" size="1.778" layer="95"/>
</segment>
</net>
<net name="SCL" class="0">
<segment>
<pinref part="U$1" gate="G$1" pin="A5"/>
<wire x1="30.48" y1="48.26" x2="20.32" y2="48.26" width="0.1524" layer="91"/>
<label x="20.32" y="48.26" size="1.778" layer="95"/>
</segment>
</net>
<net name="BUT_0_IN" class="0">
<segment>
<pinref part="U$12" gate="G$1" pin="SW_OUT"/>
<wire x1="-15.24" y1="25.4" x2="-2.54" y2="25.4" width="0.1524" layer="91"/>
<label x="-5.08" y="25.4" size="1.778" layer="95"/>
</segment>
<segment>
<pinref part="U$9" gate="G$1" pin="A_IN"/>
<wire x1="30.48" y1="7.62" x2="17.78" y2="7.62" width="0.1524" layer="91"/>
<label x="15.24" y="7.62" size="1.778" layer="95"/>
</segment>
</net>
<net name="BUT_1_IN" class="0">
<segment>
<pinref part="U$13" gate="G$1" pin="SW_OUT"/>
<wire x1="-15.24" y1="20.32" x2="-2.54" y2="20.32" width="0.1524" layer="91"/>
<label x="-5.08" y="20.32" size="1.778" layer="95"/>
</segment>
<segment>
<pinref part="U$9" gate="G$1" pin="B_IN"/>
<wire x1="60.96" y1="2.54" x2="73.66" y2="2.54" width="0.1524" layer="91"/>
<label x="66.04" y="2.54" size="1.778" layer="95"/>
</segment>
</net>
<net name="BUT_2_IN" class="0">
<segment>
<pinref part="U$14" gate="G$1" pin="SW_OUT"/>
<wire x1="-15.24" y1="15.24" x2="-2.54" y2="15.24" width="0.1524" layer="91"/>
<label x="-5.08" y="15.24" size="1.778" layer="95"/>
</segment>
<segment>
<pinref part="U$9" gate="G$1" pin="C_IN"/>
<wire x1="30.48" y1="2.54" x2="17.78" y2="2.54" width="0.1524" layer="91"/>
<label x="15.24" y="2.54" size="1.778" layer="95"/>
</segment>
</net>
<net name="BUT_3_IN" class="0">
<segment>
<pinref part="U$15" gate="G$1" pin="SW_OUT"/>
<wire x1="-15.24" y1="10.16" x2="-2.54" y2="10.16" width="0.1524" layer="91"/>
<label x="-5.08" y="10.16" size="1.778" layer="95"/>
</segment>
<segment>
<pinref part="U$9" gate="G$1" pin="D_IN"/>
<wire x1="60.96" y1="-2.54" x2="73.66" y2="-2.54" width="0.1524" layer="91"/>
<label x="66.04" y="-2.54" size="1.778" layer="95"/>
</segment>
</net>
<net name="BUT_4_IN" class="0">
<segment>
<pinref part="U$16" gate="G$1" pin="SW_OUT"/>
<wire x1="-15.24" y1="5.08" x2="-2.54" y2="5.08" width="0.1524" layer="91"/>
<label x="-5.08" y="5.08" size="1.778" layer="95"/>
</segment>
<segment>
<pinref part="U$9" gate="G$1" pin="E_IN"/>
<wire x1="30.48" y1="-2.54" x2="17.78" y2="-2.54" width="0.1524" layer="91"/>
<label x="15.24" y="-2.54" size="1.778" layer="95"/>
</segment>
</net>
<net name="BUT_5_IN" class="0">
<segment>
<pinref part="U$17" gate="G$1" pin="SW_OUT"/>
<wire x1="-15.24" y1="0" x2="-2.54" y2="0" width="0.1524" layer="91"/>
<label x="-5.08" y="0" size="1.778" layer="95"/>
</segment>
<segment>
<pinref part="U$9" gate="G$1" pin="F_IN"/>
<wire x1="60.96" y1="-7.62" x2="73.66" y2="-7.62" width="0.1524" layer="91"/>
<label x="66.04" y="-7.62" size="1.778" layer="95"/>
</segment>
</net>
<net name="N$7" class="0">
<segment>
<pinref part="U$9" gate="G$1" pin="OSC_IN"/>
<wire x1="30.48" y1="-7.62" x2="22.86" y2="-7.62" width="0.1524" layer="91"/>
<wire x1="22.86" y1="-7.62" x2="22.86" y2="-15.24" width="0.1524" layer="91"/>
<wire x1="22.86" y1="-15.24" x2="35.56" y2="-15.24" width="0.1524" layer="91"/>
</segment>
</net>
<net name="N$8" class="0">
<segment>
<pinref part="U$9" gate="G$1" pin="OSC_OUT"/>
<wire x1="60.96" y1="-10.16" x2="66.04" y2="-10.16" width="0.1524" layer="91"/>
<wire x1="66.04" y1="-10.16" x2="66.04" y2="-15.24" width="0.1524" layer="91"/>
<wire x1="66.04" y1="-15.24" x2="40.64" y2="-15.24" width="0.1524" layer="91"/>
</segment>
</net>
<net name="PW_IN" class="0">
<segment>
<pinref part="U$19" gate="G$1" pin="SW_IN"/>
<wire x1="86.36" y1="109.22" x2="86.36" y2="101.6" width="0.1524" layer="91"/>
<label x="91.44" y="101.6" size="1.778" layer="95" rot="R180"/>
</segment>
<segment>
<pinref part="U$3" gate="G$1" pin="SW1"/>
<wire x1="50.8" y1="88.9" x2="50.8" y2="83.82" width="0.1524" layer="91"/>
<label x="48.26" y="83.82" size="1.778" layer="95" rot="R90"/>
</segment>
</net>
<net name="PW_OUT" class="0">
<segment>
<pinref part="U$19" gate="G$1" pin="SW_OUT"/>
<wire x1="99.06" y1="109.22" x2="99.06" y2="101.6" width="0.1524" layer="91"/>
<label x="111.76" y="101.6" size="1.778" layer="95" rot="R180"/>
</segment>
<segment>
<pinref part="U$3" gate="G$1" pin="SW0"/>
<wire x1="53.34" y1="88.9" x2="53.34" y2="83.82" width="0.1524" layer="91"/>
<label x="55.88" y="83.82" size="1.778" layer="95" rot="R90"/>
</segment>
</net>
</nets>
</sheet>
</sheets>
</schematic>
</drawing>
<compatibility>
<note version="6.3" minversion="6.2.2" severity="warning">
Since Version 6.2.2 text objects can contain more than one line,
which will not be processed correctly with this version.
</note>
</compatibility>
</eagle>
