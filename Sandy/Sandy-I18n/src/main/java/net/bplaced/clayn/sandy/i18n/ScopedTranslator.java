package net.bplaced.clayn.sandy.i18n;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
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
    private final Map<String, String> scopeKeyMapper = new HashMap<>();

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

    private String getScopedKey(String key)
    {
        return scope + key;
    }

    @Override
    public String getString(String key)
    {
        if (!scopeKeyMapper.containsKey(key))
        {
            scopeKeyMapper.put(key, getScopedKey(key));
        }
        return base.getString(scopeKeyMapper.get(key));
    }

    @Override
    public Locale getLocale()
    {
        return base.getLocale();
    }

    @Override
    public Translator getScopedTranslator(String scope)
    {
        return new ScopedTranslator(scope, this);
    }

    @Override
    public Translator setLocale(Locale locale)
    {
        base.setLocale(locale);
        return this;
    }

    @Override
    public Translator immutable()
    {
        return base.immutable().getScopedTranslator(scope);
    }

}
