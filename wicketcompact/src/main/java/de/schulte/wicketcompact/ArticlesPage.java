package de.schulte.wicketcompact;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.schulte.wicketcompact.entities.Article;
import de.schulte.wicketcompact.services.ArticleService;
import de.schulte.wicketcompact.services.BaseService;

public class ArticlesPage extends BaseEntitiesPage<Article> {

    public ArticlesPage(final PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected Component[] getComponentsForEntityView(final Article article) {
        final WebComponent image = new WebComponent("image");
        image.add(new AttributeModifier("src", article.getImageUrl()));
        return new Component[] {
            new Label("name", article.getName()),
            new Label("description", article.getDescription()),
            new Label("price", article.getPrice()),
            image
        };
    }

    @Override
    protected Class<? extends BaseService<Article>> getServiceClass() {
        return ArticleService.class;
    }
}
