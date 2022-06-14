package de.schulte.wicketcompact;

import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.markup.html.panel.Panel;

public class EntitiesNavigation extends Panel {

    final IPageable entitiesView;

    public EntitiesNavigation(final String id, final IPageable entitiesView) {
        super(id);
        this.entitiesView = entitiesView;
    }
    
    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new PagingNavigation("navigator", entitiesView));
    }
}
