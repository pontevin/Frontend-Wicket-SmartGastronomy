package de.schulte.wicketcompact;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.schulte.wicketcompact.entities.Category;
import de.schulte.wicketcompact.services.CategoryService;
import de.schulte.wicketcompact.services.ServiceRegistry;

public class CategoriesPage extends BaseEntitiesPage {

    public CategoriesPage(final PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        
        final CategoryService categoryService = ServiceRegistry.get(CategoryService.class);
        final List<Category> categories = new ArrayList<>(categoryService.listAll());
        final IDataProvider<Category> categoriesProvider = new ListDataProvider<>(categories);
        final DataView<Category> categoriesView = new DataView<Category>("categories", categoriesProvider) {
            @Override
            protected void populateItem(final Item<Category> item) {
                item.add(new Label("name", item.getModelObject().getName()));
                final WebComponent image = new WebComponent("image");
                image.add(new AttributeModifier("src", item.getModelObject().getImageUrl()));
                item.add(image);
            }
        };
        categoriesView.setItemsPerPage(3);
        add(categoriesView);
        add(new PagingNavigator("navigator", categoriesView));
    }
}
