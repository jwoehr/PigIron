/**
 * Provides the classes which embody
 * the basic VSMAPI parameter types,
 * recursive parameter composition and
 * decomposition and parameter marshalling
 * along with TCP/IP socket communications
 * conforming to the VSMAPI protocol.
 *
 * Subpackages provide the definitions
 * of aggregate structures.
 * 
 * The actual VSMAPI function calls themselves
 * are defined in <tt>com.softwoehr.pigiron.functions</tt> .
 *
 * Constants referring to parameter contents are defined in the appropriate
 * VSMStructure extender for that parameter, or to the<tt>com.softwoehr.pigiron.functions.VSMCall</tt>
 * in the cases where such parameters are non-structure types.
 *
 * <h3>Basic VSMAPI Parameter Types</h3>
 *
 * Alongside the VSMAPI documented types:
 * <ul>
 * <li><tt>int1</tt></li>
 * <li><tt>int4</tt>,
 * <tt>int8</tt></li>
 * <li><tt>string</tt></li>
 * <li><tt>struct</tt></li>
 * <li><tt>array</tt></li>
 * </ul>
 * Pigiron recognizes <tt>counted_struct</tt>
 * as an extra type above and beyond the base types enumerated
 * by the VSMAPI documentation.
 * 
 * @see com.softwoehr.pigiron.access.paramstructs
 * @see com.softwoehr.pigiron.functions.VSMCall
 */
package com.softwoehr.pigiron.access;