package de.schulte.wicketcompact;

import org.apache.wicket.Page;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class PageLink extends BookmarkablePageLink<Void> {

    private final Class<? extends Page> pageClass;

    public PageLink(String id, Class<? extends Page> pageClass) {
        super(id, pageClass);
        this.pageClass = pageClass;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        if (getPage().getClass().equals(this.pageClass)) {
            add(new AttributeAppender("class", " active"));
        }
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        final boolean isOwnPageActive = getPage().getClass().equals(this.pageClass);
        setEnabled(!isOwnPageActive);
    }
}
