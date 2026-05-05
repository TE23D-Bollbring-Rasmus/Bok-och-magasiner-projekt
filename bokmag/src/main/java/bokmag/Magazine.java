// Rasmus — Klass som representerar en tidning och ärver från MediaItem.
package bokmag;

public class Magazine extends MediaItem {
    private int issueNumber;
    private String category;
    private int publishedYear;

    public Magazine(String id, String title, boolean isAvailable,
                    int issueNumber, String category, int publishedYear) {
        super(id, title, isAvailable);
        this.issueNumber = issueNumber;
        this.category = category;
        this.publishedYear = publishedYear;
    }
}
