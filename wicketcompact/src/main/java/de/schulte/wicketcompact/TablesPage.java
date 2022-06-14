package de.schulte.wicketcompact;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.schulte.wicketcompact.entities.Table;
import de.schulte.wicketcompact.services.BaseService;
import de.schulte.wicketcompact.services.TableService;

public class TablesPage extends BaseEntitiesPage<Table> {

    public TablesPage(final PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected Component[] getComponentsForEntityView(final Table table) {
        return new Component[] {
            new Label("name", table.getName()),
            new Label("seat-count", table.getSeatCount())
        };
    }

    @Override
    protected Class<? extends BaseService<Table>> getServiceClass() {
        return TableService.class;
    }
}
