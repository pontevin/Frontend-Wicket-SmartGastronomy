package de.schulte.wicketcompact;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.schulte.wicketcompact.entities.Category;
import de.schulte.wicketcompact.services.BaseService;
import de.schulte.wicketcompact.services.CategoryService;

public class CategoriesPage extends BaseEntitiesPage<Category>  {

    public CategoriesPage(final PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected Component[] getComponentsForEntityView(final Category category) {
        final WebComponent image = new WebComponent("image");
        image.add(new AttributeModifier("src", category.getImageUrl()));
        return new Component[] {
            new Label("name", category.getName()),
            image
        };
    }

    @Override
    protected Class<? extends BaseService<Category>> getServiceClass() {
        return CategoryService.class;
    }
}
