package org.millr.slick.impl.services;

import java.util.Map;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.millr.slick.services.SettingsService;
import org.millr.slick.services.OsgiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.osgi.framework.Constants;

@Service(value = SettingsService.class)
@Component(metatype = true,
           immediate = true,
           name = "org.millr.slick",
           label = "Slick Configuration",
           description = "General blog settings.")
@Properties({
    @Property(name = SettingsServiceImpl.SYSTEM_BLOG_NAME,
              value = SettingsServiceImpl.BLOG_NAME_DEFAULT_VALUE,
              label = "Blog Name",
              description = "The blog name is used throughout your site."),
    @Property(name = SettingsServiceImpl.SYSTEM_ACCENT_COLOR,
		      value = SettingsServiceImpl.ACCENT_COLOR_DEFAULT_VALUE,
		      label = "Accent Color",
		      description = "The accent color used throughout your site."),
    @Property(name = SettingsServiceImpl.SYSTEM_ANALYTICS_SCRIPT,
    			value = SettingsServiceImpl.ANALYTICS_SCRIPT_DEFAULT_VALUE,
    			label = "Analytics Script",
    			description = "The script provided by your analytics service. This should include the <script> tag."),
    @Property(name = SettingsServiceImpl.SYSTEM_HEADER_IMAGE,
				value = SettingsServiceImpl.HEADER_IMAGE_DEFAULT_VALUE,
				label = "Header Image",
				description = "The default post header image if one has not been set."),
    @Property(name = SettingsServiceImpl.SYSTEM_USE_DISPATCHER,
              boolValue = SettingsServiceImpl.USE_DISPATCHER_DEFAULT_VALUE,
              label = "Use Dispatcher",
              description = "Using a dispatcher will trigger an Apache cache flush on content modification."),
    @Property(name = Constants.SERVICE_DESCRIPTION,
              value = "General blog engine system settings."),
    @Property(name = Constants.SERVICE_VENDOR,
              value = "Slick")
})
public class SettingsServiceImpl implements SettingsService {

    /** Service to get and set OSGi properties. */
    @Reference
    private OsgiService osgiService;

    /** The logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(SettingsService.class);

    /** PID of the current OSGi component */
    private static final String COMPONENT_PID = "org.millr.slick";

    /** Default value for the blog name */
    public static final String BLOG_NAME_DEFAULT_VALUE = "Slick Blogging Engine";
    
    /** Default value for the analytics script */
    public static final String ANALYTICS_SCRIPT_DEFAULT_VALUE = "";
    
    /** Default value for the analytics script */
    public static final String ACCENT_COLOR_DEFAULT_VALUE = "009444";
    
    /** Default value for the analytics script */
    public static final String HEADER_IMAGE_DEFAULT_VALUE = "/etc/slick/designs/slick/img/default-header-background.jpg";

    /** Default value for using a dispatcher. */
    public static final boolean USE_DISPATCHER_DEFAULT_VALUE = false;

    /** Service activation */
    @Activate
    protected void activate(Map<String, Object> properties) {
    }

    /**
     * Get the name of the blog.
     *
     * @return The name of the blog.
     */
    public String getBlogName() {
        return osgiService.getStringProperty(COMPONENT_PID, SYSTEM_BLOG_NAME, BLOG_NAME_DEFAULT_VALUE);
    }
    
    /**
     * Set the name of the blog.
     *
     * @param name The name of the blog.
     * @return true if the save was successful.
     */
    public boolean setBlogName(final String name) {
        return osgiService.setProperty(COMPONENT_PID, SYSTEM_BLOG_NAME, name);
    }
    
    public String getAnalyticsScript() {
        return osgiService.getStringProperty(COMPONENT_PID, SYSTEM_ANALYTICS_SCRIPT, ANALYTICS_SCRIPT_DEFAULT_VALUE);
    }
    
    public boolean setAnalyticsScript(final String script) {
        return osgiService.setProperty(COMPONENT_PID, SYSTEM_ANALYTICS_SCRIPT, script);
    }
    
    public String getDefaultHeaderImage() {
        return osgiService.getStringProperty(COMPONENT_PID, SYSTEM_HEADER_IMAGE, HEADER_IMAGE_DEFAULT_VALUE);
    }
    
    public boolean setDefaultHeaderImage(final String imageUri) {
        return osgiService.setProperty(COMPONENT_PID, SYSTEM_HEADER_IMAGE, imageUri);
    }
    
    public String getAccentColor() {
    	return osgiService.getStringProperty(COMPONENT_PID, SYSTEM_ACCENT_COLOR, ACCENT_COLOR_DEFAULT_VALUE);
    }
    
    public boolean setAccentColor(final String accentColor) {
    	return osgiService.setProperty(COMPONENT_PID, SYSTEM_ACCENT_COLOR, accentColor);
    }

    public boolean getUseDispatcher() {
        return osgiService.getBooleanProperty(COMPONENT_PID, SYSTEM_USE_DISPATCHER, USE_DISPATCHER_DEFAULT_VALUE);
    }

    public boolean setUseDispatcher(final boolean value) {
        return osgiService.setProperty(COMPONENT_PID, SYSTEM_USE_DISPATCHER, value);
    }
    
    /**
     * Set multiple properties for the System Settings service.
     *
     * This is useful for setting multiple properties as the same
     * time in that the OSGi component will only be updated once
     * and thus reset only once.
     *
     * @param properties A map of properties to set.
     * @return true if save was successful.
     */
    public boolean setProperties(final Map<String, Object> properties) {
        return osgiService.setProperties(COMPONENT_PID, properties);
    }

}