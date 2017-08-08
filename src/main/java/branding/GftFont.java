package branding;

import jxl.write.WritableFont;
import jxl.write.biff.WritableFontRecord;

public class GftFont extends WritableFontRecord {

    public static final WritableFont.FontName Calibri = WritableFont.createFont("Calibri");

    protected GftFont(String fn, int ps, int bold, boolean it, int us, int ci, int ss) {
        super(fn, ps, bold, it, us, ci, ss);
    }
}
