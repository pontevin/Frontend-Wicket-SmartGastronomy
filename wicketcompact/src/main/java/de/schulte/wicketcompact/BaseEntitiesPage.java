package de.schulte.wicketcompact;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.schulte.wicketcompact.entities.BaseEntity;
import de.schulte.wicketcompact.services.BaseService;
import de.schulte.wicketcompact.services.ServiceRegistry;

public abstract class BaseEntitiesPage<E extends BaseEntity> extends BaseWebPage {

    public <T extends BaseService<E>> BaseEntitiesPage(final PageParameters parameters) {
        super(parameters);
        add(new BookmarkablePageLink<Void>("back", getApplication().getHomePage()));
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final BaseService<E> baseService = ServiceRegistry.get(getServiceClass());
        final EntitiesView<E> entitiesView = new EntitiesView<E>("entities", baseService.listAll()) {
            @Override
            protected Component[] getComponentsForEntity(final E entity) {
                return getComponentsForEntityView(entity);
            }
        };
        add(entitiesView);
        add(new EntitiesNavigation("navigation", entitiesView));
    }

    protected abstract Class<? extends BaseService<E>> getServiceClass();

    protected abstract Component[] getComponentsForEntityView(final E entity);
}
