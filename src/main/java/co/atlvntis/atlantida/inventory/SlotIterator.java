package co.atlvntis.atlantida.inventory;

import lombok.Getter;

@Getter
public class SlotIterator {

    private final InventoryContent content;
    private final ClickableItem[][] items;
    private final int rowStart, columnStart;
    private final int rowFinish, columnFinish;
    private final int size;
    private int row, column;
    private boolean started = false;
    private boolean ended = false;

    public SlotIterator(InventoryContent content, int rowStart, int columnStart, int rowFinish, int columnFinish) {
        this.content = content;
        this.items = content.getItems();
        this.rowStart = rowStart;
        this.columnStart = columnStart;
        this.rowFinish = rowFinish;
        this.columnFinish = columnFinish;
        this.size = (rowFinish - rowStart) * (columnFinish - columnStart);
        this.row = rowStart;
        this.column = columnStart;
    }

    public Slot next() {
        if(!started) {
            started = true;
            return new Slot(row, column);
        }
        if(column == columnFinish) {
            column = 0;
            row++;
        } else {
            column++;
        }
        if(row > rowFinish) {
            ended = true;
        }
        return new Slot(row, column);
    }

    public void setNext(ClickableItem clickableItem) {
        if(!ended)
            content.set(next(), clickableItem);
    }

    public boolean ended() {
        return ended;
    }

    public void reset() {
        this.started = false;
        this.ended = false;
        this.row = rowStart;
        this.column = columnStart;
    }

}