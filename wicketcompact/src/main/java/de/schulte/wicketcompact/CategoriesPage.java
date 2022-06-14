package de.schulte.wicketcompact;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
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
        final EntitiesView<Category> categoriesView = new EntitiesView<Category>("categories", categoryService.listAll(), 2) {
            @Override
            protected Component[] getComponentsForEntity(final Category category) {
                final WebComponent image = new WebComponent("image");
                image.add(new AttributeModifier("src", category.getImageUrl()));
                return new Component[] {
                    new Label("name", category.getName()),
                    image
                };
            }
        };
        add(categoriesView);
        add(new PagingNavigation("navigator", categoriesView));
    }
}
