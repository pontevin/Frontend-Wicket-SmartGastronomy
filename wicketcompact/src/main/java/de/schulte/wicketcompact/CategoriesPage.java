package de.schulte.wicketcompact;

import de.schulte.wicketcompact.entities.Category;
import de.schulte.wicketcompact.services.CategoryService;
import de.schulte.wicketcompact.services.ServiceRegistry;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class CategoriesPage extends BaseEntitiesPage {

    private final CategoryService categoryService;

    public CategoriesPage(PageParameters parameters) {
        super(parameters);
        categoryService = ServiceRegistry.get(CategoryService.class);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        final RepeatingView categories = new RepeatingView("categories");
        for (Category category : categoryService.listAll()) {
            categories.add(new Label(categories.newChildId(), category.getName()));
        }
        add(categories);
    }
}
