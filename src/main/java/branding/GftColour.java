package branding;

import jxl.format.Colour;

public class GftColour extends Colour{

    public static final Colour GFT_HEADER_BLUE = new GftColour(Colour.getInternalColour(), "header_blue", 91, 155, 213);
    public static final Colour GFT_ROW_BLUE = new GftColour(66, "row_blue", 221, 235, 247);

    protected GftColour(int val, String s, int r, int g, int b) {
        super(val, s, r, g, b);
        getAllColours()
    }
}
