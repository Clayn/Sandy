package net.bplaced.clayn.sandy.i18n;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.1
 */
public interface Translator
{

    /**
     * Returns all the keys this translator has a text for.
     *
     * @return a set of all available keys of this translator
     */
    public Set<String> getTranslationKeys();

    /**
     * Returns the text for the given key from the translator.
     *
     * @param key the key to get
     * @return the text saved for this key
     */
    public String getString(String key);

    /**
     * Returns wether or not this translator contains the given key
     *
     * @param key the key to check
     * @return {@code true} if and only of the translator contains this key and
     * can provide a message, {@code false} otherwise.
     */
    public default boolean containsKey(String key)
    {
        return getTranslationKeys().contains(key);
    }

    public Locale getLocale();

    public Translator getScopedTranslator(String scope);

    public static Translator immutableTranslator(Translator t)
    {
        return new Translator()
        {
            private final Map<String, String> translations = new HashMap<>(
                    t.getTranslationKeys().size());
            private final Locale locale = t.getLocale();

            
            {
                translations.putAll(t.getTranslationKeys().stream().collect(
                        Collectors.toMap(Function.identity(), t::getString)));
            }

            @Override
            public Set<String> getTranslationKeys()
            {
                return translations.keySet();
            }

            private Supplier<MissingResourceException> getException(String key)
            {
                return () -> new MissingResourceException(
                        "Can't find resource for key " + key,
                        getClass().getName(), key);
            }

            @Override
            public String getString(String key)
            {
                return Optional.ofNullable(key).orElseThrow(getException(
                        key));
            }

            @Override
            public Locale getLocale()
            {
                return locale;
            }

            @Override
            public Translator getScopedTranslator(String scope)
            {
                return Translator.immutableTranslator(t.getScopedTranslator(
                        scope).setLocale(locale));
            }

            @Override
            public Translator setLocale(Locale locale)
            {
                return this;
            }
        };
    }

    public Translator setLocale(Locale locale);
}
