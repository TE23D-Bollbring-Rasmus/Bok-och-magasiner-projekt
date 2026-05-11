// Rasmus: Basklass för alla medier i bibliotekssystemet.
package bokmag;

public class MediaItem {
    private String id;
    private String title;
    private boolean isAvailable;

    public MediaItem(String id, String title, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.isAvailable = isAvailable;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public boolean isAvailable() { return isAvailable; }

    public void toggleAvailable() {
        isAvailable = !isAvailable;
    }
}
