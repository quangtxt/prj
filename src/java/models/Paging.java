
package models;

import dal.DBContext;

/**
 *
 * @author DELL
 */
public class Paging extends DBContext{
    private int page;
    private int endPage;
    private int size;
    private int range;
    
    public int getPage() {
        return page;
    }

    public void setPage(String page) {
        if (page == null || Integer.parseInt(page) < 1 || Integer.parseInt(page) > getEndPage()) {
            this.page = 1;
        } else {
            this.page = Integer.parseInt(page);
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getRange() {
        return range;
    }
    

    public void setRange(int range) {
        this.range = range;
    }

    public int getEndPage() {
        endPage = size / range;
        if (size % range != 0) {
            endPage++;
        }
        return endPage;
    }
    
    
}
