package net.brockmatt.stripes.interceptor;

import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.jstl.core.Config;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Injects our own resource bundle into the context so JSP formatters work correctly with the
 * self-referencing ResourceBundles (see: {@link net.brockmatt.util.ResourceBundleUtil} for more info)
 */
@Intercepts(LifecycleStage.ResolutionExecution)
public class ResourceBundleInterceptor implements Interceptor {

  public Resolution intercept(ExecutionContext context) throws Exception {
    HttpServletRequest request = context.getActionBeanContext().getRequest();
    Locale locale = request.getLocale();

    ResourceBundle bundle = StripesFilter.getConfiguration().getLocalizationBundleFactory().getErrorMessageBundle(locale);

    Config.set(request, Config.FMT_LOCALIZATION_CONTEXT, new LocalizationContext(bundle, locale));

    return context.proceed();
  }

}