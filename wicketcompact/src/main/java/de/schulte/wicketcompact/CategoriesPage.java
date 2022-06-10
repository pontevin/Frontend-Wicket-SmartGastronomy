package de.schulte.wicketcompact;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.schulte.wicketcompact.entities.Category;
import de.schulte.wicketcompact.services.CategoryService;
import de.schulte.wicketcompact.services.ServiceRegistry;

public class CategoriesPage extends BaseEntitiesPage {

    private final CategoryService categoryService;

    public CategoriesPage(PageParameters parameters) {
        super(parameters);
        categoryService = ServiceRegistry.get(CategoryService.class);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        
        final List<Category> categories = new ArrayList<>(categoryService.listAll());
        final ListView<Category> categoryList = new ListView<Category>("categories", categories) {
            @Override
            protected void populateItem(final ListItem<Category> item) {
                item.add(new Label("name", item.getModelObject().getName()));
                final WebComponent image = new WebComponent("image");
                image.add(new AttributeModifier("src", item.getModelObject().getImageUrl()));
                item.add(image);
            }
        };
        add(categoryList);
    }
}
