package net.bplaced.clayn.sandy.i18n;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.1
 */
public class SimpleTranslator implements Translator
{

    private ResourceBundle bundle;
    private Locale locale;
    private final Set<String> keys = new HashSet<>();

    public SimpleTranslator(ResourceBundle bundle)
    {
        setBundle(bundle);
        locale = bundle.getLocale();
    }

    public SimpleTranslator(String baseName)
    {
        this(ResourceBundle.getBundle(baseName));
    }

    public SimpleTranslator(String baseName, Locale locale)
    {
        this(ResourceBundle.getBundle(baseName, locale));
    }

    private void setBundle(ResourceBundle bundle)
    {
        this.bundle = Objects.requireNonNull(bundle);
        keys.clear();
        Enumeration<String> e = bundle.getKeys();
        while (e.hasMoreElements())
        {
            keys.add(e.nextElement());
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
        return locale;
    }

    @Override
    public Translator getScopedTranslator(String scope)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Translator setLocale(Locale locale)
    {
        Objects.requireNonNull(locale);
        setBundle(ResourceBundle.getBundle(bundle.getBaseBundleName(), locale));
        return this;
    }

}
