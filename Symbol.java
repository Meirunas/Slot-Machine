import javax.swing.ImageIcon;

public class Symbol implements ISymbol, Comparable<Symbol> {

    ImageIcon symbol;
    private int value;
//set up variables
    public Symbol(ImageIcon faceImage, int value) {
        this.symbol = faceImage;
        this.value = value;
    }

    public void setImage(ImageIcon symbol) {
        this.symbol = symbol;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ImageIcon getImage() {
        return symbol;
    }

    public int getValue() {
        return value;
    }

    public int compareTo(Symbol symbol) {
        return value - symbol.getValue();
    }
}
