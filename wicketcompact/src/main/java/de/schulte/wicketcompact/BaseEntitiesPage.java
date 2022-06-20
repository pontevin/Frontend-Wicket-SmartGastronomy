package de.schulte.wicketcompact;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.schulte.wicketcompact.entities.BaseEntity;
import de.schulte.wicketcompact.services.BaseService;
import de.schulte.wicketcompact.services.ServiceRegistry;

public abstract class BaseEntitiesPage<E extends BaseEntity> extends BaseWebPage {

    final long itemsPerPage;

    public <T extends BaseService<E>> BaseEntitiesPage(final PageParameters parameters) {
        this(parameters, 3);
    }

    public <T extends BaseService<E>> BaseEntitiesPage(final PageParameters parameters, final long itemsPerPage) {
        super(parameters);
        this.itemsPerPage = itemsPerPage;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final BaseService<E> baseService = ServiceRegistry.get(getServiceClass());
        final List<E> entities = new ArrayList<>(baseService.listAll());
        final ListDataProvider<E> dataProvider = new ListDataProvider<>(entities);
        final DataView<E> entitiesView = new DataView<E>("entities", dataProvider, itemsPerPage) {
            @Override
            protected void populateItem(final Item<E> item) {
                final E entity = item.getModelObject();
                item.add(getComponentsForEntityView(entity));
            }
        };
        add(entitiesView);
        add(new EntitiesNavigation("navigation", entitiesView));
    }

    protected abstract Class<? extends BaseService<E>> getServiceClass();

    protected abstract Component[] getComponentsForEntityView(final E entity);
}
