package net.bplaced.clayn.sandy.i18n;

import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.1
 */
public class ScopedTranslator implements Translator
{

    private final String scope;
    private final Translator base;

    ScopedTranslator(String scope, Translator base)
    {
        this.scope = scope;
        this.base = base;
    }

    @Override
    public Set<String> getTranslationKeys()
    {
        return base.getTranslationKeys().stream().filter(
                (str) -> str.startsWith(scope)).collect(Collectors.toSet());
    }

    @Override
    public String getString(String key)
    {
        return base.getString(key);
    }

    @Override
    public Locale getLocale()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Translator getScopedTranslator(String scope)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Translator setLocale(Locale locale)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
