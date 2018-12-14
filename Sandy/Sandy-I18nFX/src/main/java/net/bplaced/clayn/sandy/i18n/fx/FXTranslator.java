package net.bplaced.clayn.sandy.i18n.fx;

import java.util.Locale;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import net.bplaced.clayn.sandy.i18n.Translator;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.1
 */
public interface FXTranslator extends Translator
{

    public ObjectProperty<Locale> localeProperty();

    @Override
    public default Locale getLocale()
    {
        return localeProperty().get();
    }

    @Override
    public default FXTranslator setLocale(Locale locale)
    {
        localeProperty().set(locale);
        return this;
    }

    /**
     * Returns a string property representing the translation for the given key.
     * If the locale of this translator is changed, the property will also be
     * updated.
     *
     * @param key the key to get the translation for
     * @return a property with the current locale translation
     */
    public ReadOnlyStringProperty getStringProperty(String key);

}
