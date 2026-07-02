package Utils.ID.Base;

public class ID {
    final private String prefix;
    final private int value;

    private static int nextIndex = 0;

    public ID() {
        prefix = "";
        value = 0;
    }
    public ID(String prefix) {
        this.prefix = prefix;
        value = getNextIndex();
    }
    public ID(String prefix, int value){
        this.prefix = prefix;
        this.value = value;
    }

    private int getNextIndex() {
        return nextIndex++;
    }

   public String getID() {
        return prefix + value;
   }

   public int getValue() {
        return value;
   }

    public boolean equals(ID other) {
        return other.getID().equals(this.getID());
    }

    public void setNextIndex(int index) {
        nextIndex = index;
    }
}
