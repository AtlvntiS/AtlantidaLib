package co.atlvntis.atlantida.inventory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
public class Pagination {

    private final InventoryContent content;

    private int actualPage = 0;
    private ClickableItem[] items;
    private int itemsPerPage;

    private SlotIterator iterator;

    public Pagination(InventoryContent content) {
        this.content = content;
        this.iterator = new SlotIterator(content, 0, 0, content.getWrapper().getParent().getRows(), 9);
        this.itemsPerPage = iterator.getSize();
    }

    public void setItems(ClickableItem... items) {
        this.items = items;
    }

    public void setPage(int page) {
        this.actualPage = page;
        populate();
    }

    public void setPageAndUpdate(int page) {
        setPage(page);
        content.getWrapper().update();
    }

    public void setIterator(SlotIterator iterator) {
        this.iterator = iterator;
        this.itemsPerPage = iterator.getSize();
    }

    public void populate() {

        if(items == null || items.length == 0) return;

        for (int i = 0; i < iterator.getSize(); i++) {
            ClickableItem clickableItem = items[(actualPage * itemsPerPage) + i];
            iterator.setNext(clickableItem);
        }

    }

}
