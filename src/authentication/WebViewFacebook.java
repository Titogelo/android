package authentication;

import org.celstec.arlearn2.android.delegators.ARL;

/**
 * ****************************************************************************
 * Copyright (C) 2013 Open Universiteit Nederland
 * <p/>
 * This library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library.  If not, see <http://www.gnu.org/licenses/>.
 * <p/>
 * Contributors: Stefaan Ternier
 * ****************************************************************************
 */
public class WebViewFacebook extends WebViewFragment{
    @Override
    protected String getUrl() {
        return "https://graph.facebook.com/oauth/authorize?client_id="
                + ARL.config.getProperty("facebook_clientId")
                +"&display=page&redirect_uri="
                + ARL.config.getProperty("facebook_redirecturi")
                + "&scope=email";
    }
}
