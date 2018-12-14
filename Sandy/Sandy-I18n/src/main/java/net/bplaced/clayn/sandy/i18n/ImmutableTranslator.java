package net.bplaced.clayn.sandy.i18n;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.1
 */
final class ImmutableTranslator implements Translator
{

    private final ResourceBundle bundle;
    private final Set<String> keys = new HashSet<>();

    ImmutableTranslator(ResourceBundle bundle)
    {
        this.bundle = bundle;
        Enumeration<String> en = bundle.getKeys();
        while (en.hasMoreElements())
        {
            keys.add(en.nextElement());
        }
    }

    @Override
    public Set<String> getTranslationKeys()
    {
        return keys;
    }

    @Override
    public String getString(String key)
    {
        return bundle.getString(key);
    }

    @Override
    public Locale getLocale()
    {
        return bundle.getLocale();
    }

    @Override
    public Translator getScopedTranslator(String scope)
    {
        Translator self = this;
        return new ScopedTranslator(scope, this)
        {
            @Override
            public Translator setLocale(Locale locale)
            {
                return self.setLocale(locale).getScopedTranslator(scope);
            }

        };
    }

    @Override
    public Translator setLocale(Locale locale)
    {
        return new ImmutableTranslator(ResourceBundle.getBundle(
                bundle.getBaseBundleName(),
                getLocale()));
    }

}
