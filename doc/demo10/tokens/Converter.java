/*
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License (the License). You may not use this file except in compliance with the
 * License.
 *
 * You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
 * specific language governing permission and limitations under the License.
 *
 * When distributing Covered Software, include this CDDL Header Notice in each file and include
 * the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 * Header, with the fields enclosed by brackets [] replaced by your own identifying
 * information: "Portions copyright [year] [name of copyright owner]".
 *
 * Copyright 2015 ForgeRock AS.
 */

package org.forgerock.openam.tokens;

/**
 * A custom converter that allows non-CTS-native types to be converted to those that can be stored.
 * @param <F> The type being converted from.
 * @param <T> The type being converted to.
 */
public interface Converter<F, T> {

    /**
     * Converts the object from its original format.
     *
     * @param f The object being converted.
     * @return The converted object.
     */
    T convertFrom(F f);

    /**
     * Converts the object back to its original format.
     *
     * @param t The object being converted.
     * @return The converted object.
     */
    F convertBack(T t);

    /**
     * An identity converter that converts an object to the same object.
     */
    class IdentityConverter implements Converter<Object, Object> {
        @Override
        public Object convertFrom(Object o) {
            return o;
        }

        @Override
        public Object convertBack(Object o) {
            return o;
        }
    }
}
