/**
 * In trying to keep things simple and avoid complex builds, remote
 * artifacts and the other impedimenta of clarity and coding ease, the
 * only non-PigIron library code outside the JDK that PigIron uses is
 * this JSON implementation from <a
 * href="http://json.org">JSON.org</a> for data interchange Web
 * objects. The JSON code is checked directly into the PigIron tree
 * (pigiron/src/org/json) and built along with PigIron since it is
 * small and may need enhancement and maintenance for PigIron.<br>
 *<br>
 * JSON is used under the JSON license:<br>
 *<tt> <br>
 * Copyright (c) 2002 JSON.org<br>
 *<br>
 *  Permission is hereby granted, free of charge, to any person<br>
 *  obtaining a copy of this software and associated documentation<br>
 *  files (the "Software"), to deal in the Software without<br>
 *  restriction, including without limitation the rights to use, copy,<br>
 *  modify, merge, publish, distribute, sublicense, and/or sell copies<br>
 *  of the Software, and to permit persons to whom the Software is<br>
 *  furnished to do so, subject to the following conditions:<br>
 *<br> 
 * The above copyright notice and this permission notice shall be<br>
 * included in all copies or substantial portions of the Software.<br>
 *<br> 
 * The Software shall be used for Good, not Evil.<br>
 *<br> 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,<br>
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF<br>
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND<br>
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS<br>
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN<br>
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN<br>
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE<br>
 * SOFTWARE.</tt>
 */
package org.json;
