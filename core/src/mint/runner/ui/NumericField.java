package mint.runner.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class NumericField extends TextField{
    public NumericField(BitmapFont font) {
        super(font);
    }

    @Override
    public void addSymbol(char symbol) {
        if (isNum(symbol))
        super.addSymbol(symbol);
    }

    public boolean isNum(char symbol) {
        try {
            int i = Integer.parseInt(String.valueOf(symbol));

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
