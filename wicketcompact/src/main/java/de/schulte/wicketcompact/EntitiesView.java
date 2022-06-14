package de.schulte.wicketcompact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.wicket.Component;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;

public abstract class EntitiesView<E extends Serializable> extends DataView<E> {

    public EntitiesView(final String id, final Collection<E> entities, final long itemsPerPage) {
        super(id, new ListDataProvider<E>(new ArrayList<>(entities)), itemsPerPage);
    }

    public EntitiesView(final String id, final Collection<E> entities) {
        this(id, entities, 3);
    }

    @Override
    protected void populateItem(final Item<E> item) {
        item.add(getComponentsForEntity(item.getModelObject()));
    }

    protected abstract Component[] getComponentsForEntity(final E entity);
}
