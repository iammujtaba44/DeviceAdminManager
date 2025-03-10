package com.ib.device_admin_manager.multipreferences;

import static com.ib.device_admin_manager.multipreferences.MultiProvider.CODE_BOOLEAN;
import static com.ib.device_admin_manager.multipreferences.MultiProvider.CODE_INTEGER;
import static com.ib.device_admin_manager.multipreferences.MultiProvider.CODE_LONG;
import static com.ib.device_admin_manager.multipreferences.MultiProvider.CODE_PREFS;
import static com.ib.device_admin_manager.multipreferences.MultiProvider.CODE_REMOVE_KEY;
import static com.ib.device_admin_manager.multipreferences.MultiProvider.CODE_STRING;
import static com.ib.device_admin_manager.multipreferences.MultiProvider.createContentValues;
import static com.ib.device_admin_manager.multipreferences.MultiProvider.createQueryUri;
import static com.ib.device_admin_manager.multipreferences.MultiProvider.extractBooleanFromCursor;
import static com.ib.device_admin_manager.multipreferences.MultiProvider.extractIntFromCursor;
import static com.ib.device_admin_manager.multipreferences.MultiProvider.extractLongFromCursor;
import static com.ib.device_admin_manager.multipreferences.MultiProvider.extractStringFromCursor;
import static com.ib.device_admin_manager.multipreferences.MultiProvider.performQuery;

import android.content.ContentResolver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Multi Preference class
 * <p>
 * - allows access to Shared Preferences across processes through a
 * Content Provider
 */
public class MultiPreferences {

    private final ContentResolver resolver;
    private final String mName;

    public MultiPreferences(String prefFileName, ContentResolver resolver) {
        this.mName = prefFileName;
        this.resolver = resolver;
    }

    public void setString(final String key, @NonNull final String value) {
        resolver.update(createQueryUri(mName, key, CODE_STRING), createContentValues(key, value), null, null);
    }

    @Nullable
    public String getString(final String key, final String defaultValue) {
        return extractStringFromCursor(performQuery(createQueryUri(mName, key, CODE_STRING), resolver), defaultValue);
    }

    public void setInt(final String key, final int value) {
        resolver.update(createQueryUri(mName, key, CODE_INTEGER), createContentValues(key, value), null, null);
    }

    public int getInt(final String key, final int defaultValue) {
        return extractIntFromCursor(performQuery(createQueryUri(mName, key, CODE_INTEGER), resolver), defaultValue);
    }

    public void setLong(final String key, final long value) {
        resolver.update(createQueryUri(mName, key, CODE_LONG), createContentValues(key, value), null, null);
    }

    public long getLong(final String key, final long defaultValue) {
        return extractLongFromCursor(performQuery(createQueryUri(mName, key, CODE_LONG), resolver), defaultValue);
    }

    public void setBoolean(final String key, final boolean value) {
        resolver.update(createQueryUri(mName, key, CODE_BOOLEAN), createContentValues(key, value), null, null);
    }

    public boolean getBoolean(final String key, final boolean defaultValue) {
        return extractBooleanFromCursor(performQuery(createQueryUri(mName, key, CODE_BOOLEAN), resolver), defaultValue);
    }

    public void removePreference(final String key) {
        resolver.delete(createQueryUri(mName, key, CODE_REMOVE_KEY), null, null);
    }

    public void clearPreferences() {
        resolver.delete(createQueryUri(mName, "", CODE_PREFS), null, null);
    }
}