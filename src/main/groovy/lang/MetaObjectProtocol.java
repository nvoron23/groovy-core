/*
 * Copyright 2003-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package groovy.lang;

/**
 * <p>An interface that defines the API usable by clients of Groovy's Meta Object Protocol (MOP). These methods are
 * implemented by the reference implementation of the @link groovy.lang.MetaClass interface.</p>
 *
 * @see MetaClassImpl
 *
 * @author John Wilson
 * @author Graeme Rocher
 */
public interface MetaObjectProtocol {

    /**
     * Returns a MetaProperty for the given name or null if it doesn't exist
     *
     * @param name The name of the MetaProperty
     * @return A MeatProperty or null
     */
    MetaProperty getMetaProperty(String name);

    /**
     * Retreives a static MetaMethod for the given name and argument values, using the types of the arguments
     * to establish the chosen MetaMethod
     *
     * @param name The name of the MetaMethod
     * @param args The argument values
     * @return A MetaMethod or null if it doesn't exist
     */
    MetaMethod getStaticMetaMethod(String name, Object[] args);

    /**
     * Retrieves a static MetaMethod for the given name and argument types
     *
     * @param name The name of the MetaMethod
     * @param argTypes The argument types
     * @return A MetaMethod or null if it doesn't exist
     */
    MetaMethod getStaticMetaMethod(String name, Class[] argTypes);

    /**
     * Retrieves an instance MetaMethod for the given name and argument values, using the types of the
     * argument values to establish the chosen MetaMethod
     *
     * @param name The name of the MetaMethod
     * @param args The argument values
     * @return A MetaMethod or null if it doesn't exist
     */
    MetaMethod getMetaMethod(String name, Object[] args);

    /**
     * Retrieves an instance MetaMethod for the given name and argument types
     *
     * @param name The name of the MetaMethod
     * @param argTypes The argument types
     * @return A MetaMethod or null if it doesn't exist
     */
    MetaMethod getMetaMethod(String name, Class[] argTypes);

    /**
     * Retrieves that Java Class that the attached Meta behaviours apply to
     *
     * @return The java.lang.Class instance
     */
    Class getTheClass();

    /**
     * Invokes a constructor for the given arguments. The MetaClass will attempt to pick the best argument which
     * matches the types of the objects passed within the arguments array
     *
     * @param arguments The arguments to the constructor
     * @return An instance of the java.lang.Class that this MetaObjectProtocol object applies to
     */
    Object invokeConstructor(Object[] arguments);

    /**
     * <p>Invokes a method on the given Object with the given name and arguments. The MetaClass will attempt to pick
     * the best method for the given name and arguments. If a method cannot be invoked a MissingMethodException will be
     * thrown.</p>
     *
     *
     * @see groovy.lang.MissingMethodException
     *
     * @param object The instance which the method is invoked on
     * @param methodName The name of the method
     * @param arguments The arguments to the method
     * @return The return value of the method which is null if the return type is void
     */
    Object invokeMethod(Object object, String methodName, Object[] arguments);

    /**
     * <p>Invokes a method on the given object, with the given name and single argument.</p>
     *
     * @see #invokeMethod(Object, String, Object[])
     *
     * @param object The Object to invoke the method on
     * @param methodName The name of the method
     * @param arguments The argument to the method
     * @return The return value of the method which is null if the return type is void
     */
     Object invokeMethod(Object object, String methodName, Object arguments);

    /**
     * <p>Invokes a static method on the given Object with the given name and arguments.</p>
     *
     * <p> The Object can either be an instance of the class that this
     * MetaObjectProtocol instance applies to or the java.lang.Class instance itself. If a method cannot be invoked
     * a MissingMethodException is will be thrown</p>
     *
     * @see groovy.lang.MissingMethodException
     *
     * @param object An instance of the class returned by the getTheClass() method or the class itself
     * @param methodName The name of the method
     * @param arguments The arguments to the method
     * @return The return value of the method which is null if the return type is void
     */
    Object invokeStaticMethod(Object object, String methodName, Object[] arguments);

    /**
     * <p>Retrieves a property of an instance of the class returned by the getTheClass() method. </p>
     *
     * <p>What this means is largely down to the MetaClass implementation, however the default case would result
     * in an attempt to invoke a JavaBean getter, or if no such getter exists a public field of the instance.</p>
     *
     * @see MetaClassImpl
     *
     * @param object An instance of the class returned by the getTheClass() method
     * @param property The name of the property to retrieve the value for
     * @return The properties value
     */
    Object getProperty(Object object, String property);

    /**
     * <p>Sets a property of an instance of the class returned by the getTheClass() method.</p>
     *
     * <p>What this means is largely down to the MetaClass implementation, however the default case would result
     * in an attempt to invoke a JavaBean setter, or if no such setter exists to set a public field of the instance.</p>
     *
     * @see MetaClassImpl
     *
     * @param object An instance of the class returned by the getTheClass() method
     * @param property The name of the property to set
     * @param newValue The new value of the property
     */
    void setProperty(Object object, String property, Object newValue);

    /**
     * <p>Retrieves an attribute of an instance of the class returned by the getTheClass() method. </p>
     *
     * <p>What this means is largely down to the MetaClass implementation, however the default case would result
     * in attempt to read a field of the instance.</p>
     *
     * @see MetaClassImpl
     *
     * @param object An instance of the class returned by the getTheClass() method
     * @param attribute The name of the attribute to retrieve the value for
     * @return The attribute value
     */
    Object getAttribute(Object object, String attribute);

    /**
     * <p>Sets an attribute of an instance of the class returned by the getTheClass() method.</p>
     *
     * <p>What this means is largely down to the MetaClass implementation, however the default case would result
     * in an attempt to set a field of the instance.</p>
     *
     * @see MetaClassImpl
     *
     * @param object An instance of the class returned by the getTheClass() method
     * @param attribute The name of the attribute to set
     * @param newValue The new value of the attribute
     */
    void setAttribute(Object object, String attribute, Object newValue);
}
