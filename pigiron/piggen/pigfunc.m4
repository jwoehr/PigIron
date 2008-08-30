divert(-1)
changecom(`\\\\')
\\ pigfunc.m4
\\   Macros to autogenerate Java code for PigIron parameters
\\   Copyright *C* 1999, 2001, 2008 Jack J. Woehr
\\   Part of the PigIron Project http://pigiron.sourceforge.net
\\   jwoehr@softwoehr.com
include(`pigsty.m4')

\\ Stream definitions
define(`null_stream',           `-1')dnl        \\ The bit bucket
define(`normal_stream',          `0')dnl        \\ Standard output
define(`file_header_stream',    `92')dnl        \\ Stream on which we define file header
define(`package_stream',        `93')dnl        \\ Stream on which we call out package
define(`imports_stream',        `94')dnl        \\ Stream on which we call out imports
define(`class_header_stream',   `95')dnl        \\ Stream on which we define class header
define(`constant_stream',       `97')dnl        \\ Stream on which we define static finals
define(`attribute_stream',      `98')dnl        \\ Stream on which we define attributes
define(`accessor_stream',      `100')dnl        \\ Stream on which we define accessors
define(`ctor_stream',          `110')dnl        \\ Stream on which we define ctor
define(`function_stream',      `120')dnl        \\ Stream on which we define functions
define(`compose_in_stream',    `130')dnl        \\ Stream on which we define composeInput
define(`compose_out_stream',   `135')dnl        \\ Stream on which we define composeInput
define(`get_function_name_stream', `139')dnl    \\ Stream on which we define getFunctionName()
define(`class_footer_stream',  `140')dnl        \\ Stream on which we define class footer
define(`file_footer_stream',   `150')dnl        \\ Stream on which we define file footer

\\ Set up our basic diversion
divert(0)dnl
push_divert(null_stream)dnl

\\ pigfunc_import(`import_name')
define(`pigfunc_import',`dnl
push_divert(`imports_stream')dnl
import $1;
pop_divert()dnl
')

\\ pigfunc_class(`name',`extends',`package',`function_name',`comment')
define(`pigfunc_class',`dnl
push_divert(class_header_stream)dnl
pushdef(`x_name', $1)dnl
define(`myClassName',x_name())dnl
pushdef(`x_extends', $2)dnl
pushdef(`x_package', $3)dnl
pushdef(`x_function_name', $4)dnl
pushdef(`x_comment', $5)dnl
push_divert(`package_stream')dnl
package x_package();

pop_divert()dnl
x_comment()
public class x_name() extends x_extends() {

    /**
     * The transmitted name of the function.
     */
    public static final String FUNCTION_NAME = "x_function_name()";

popdef(`x_comment')dnl
popdef(`x_function_name')dnl
popdef(`x_package')dnl
popdef(`x_extends')dnl
popdef(`x_name')dnl
pop_divert()dnl
')

\\ optional_ctor_params_comments(type, name, instance, t, n, i ...)
\\ Because the big ctor has to be extendable and must instance vars
define(`optional_ctor_param_comments',`dnl
ifelse(eval($# >= 3), 0, , eval($# == 3), 1, `
     * @param `$2' instances <tt>`$3'</tt>', `
     * @param `$2' instances <tt>`$3'</tt>`'optional_ctor_param_comments(shift(shift(shift($@))))')dnl
')

\\ optional_ctor_params_args(type, name, instance, t, n, i ...)
\\ Because the big ctor has to be extendable and must instance vars
define(`optional_ctor_param_args',`dnl
ifelse(eval($# >= 3), 0, , eval($# == 3), 1, `,' ``$1' `$2'', `,' ``$1' `$2'optional_ctor_param_args(shift(shift(shift($@))))')')

\\ optional_ctor_param_instantiations(type, name, instance, t, n, i ...)
\\ Because the big ctor has to be extendable and must instance vars
define(`optional_ctor_param_instantiations',`dnl
ifelse(eval($# >= 3), 0, , eval($# == 3), 1, `        set_`$3'`('`$2');
',`        set_`$3'`('`$2');`'
optional_ctor_param_instantiations(shift(shift(shift($@))))')dnl
')

\\ pigfunc_ctors(OPTIONAL type, name, instance, t, n, i ..)
define(`pigfunc_ctors',`dnl
push_divert(ctor_stream)dnl
pushdef(`x_optional_params', `$@')dnl
    /**
     *  Create an instance of the function call with important fields not instanced.
     */
    public myClassName()`(') {
    }

    /**
     * Create an instance with the variables filled in.
     * @param hostname  VSMAPI Host DNS name
     * @param port port VSMAPI Host is listening on
     * @param userid userid executing the function
     * @param password the password
     * @param target_identifier the target of the VSMAPI function`'dnl
optional_ctor_param_comments(x_optional_params())
     */
    public myClassName()`('String hostname, int port, String userid, String password, String target_identifier`'optional_ctor_param_args(x_optional_params)`)' {
        this`(');
        setHostname`('hostname);
        setPort`('port);
        setUserid`('userid);
        setPassword`('password);
        setTarget_identifier`('target_identifier);
optional_ctor_param_instantiations(x_optional_params())    }

popdef(`x_optional_params')dnl
pop_divert()dnl
')

\\ pigfunc_constant(`accessor', `type', `name', `initial_value', `comment')
define(`pigfunc_constant',`dnl
push_divert(constant_stream)dnl
    `/**' $5 `*/'
    $1 `static final' $2 $3 `=' $4`;'

pop_divert()dnl
')

\\ pigfunc_attribute(`accessor', `scope', `type', `name', `initial_value', `set_accessor', `comment')
define(`pigfunc_attribute',`dnl
push_divert(attribute_stream)dnl
pushdef(`x_accessor', ifelse($1, `', `private ', $1 ))dnl
pushdef(`x_scope', ifelse($2, `', `', $2 ))dnl
pushdef(`x_type', $3)dnl
pushdef(`x_name', $4)dnl
pushdef(`x_initial_value', $5)dnl
pushdef(`x_set_accessor', ifelse($6, `', `public', $6))dnl
pushdef(`x_comment', $7)dnl
pushdef(`x_qualified_name', x_accessor()x_scope()x_type() x_name())dnl
pushdef(`x_get_accessor_name', ifelse(x_type, `boolean', `is_', `get_')`'x_name())dnl
    `/**' x_comment() `*/'
    x_qualified_name() `=' x_initial_value()`;'

push_divert(accessor_stream)dnl
    `/** Set the value of <tt>' x_name() `</tt>.'
    ` * @param val The value to set <tt>' x_name() `</tt>.'
    ` */'
    x_set_accessor() void `set_'x_name()`('x_type() `val'`) {'
        x_name() = `val;'
    `}'

    `/** Get the value of <tt>' x_name() `</tt>.'
    ` * @return The value of <tt>' x_name() `</tt>.'
    ` */'
    public x_type() x_get_accessor_name()`() {'
        return x_name()`;'
    `}'

pop_divert()dnl
popdef(`x_get_accessor_name')dnl
popdef(`x_qualified_name')dnl
popdef(`x_comment')dnl
popdef(`x_set_accessor')dnl
popdef(`x_initial_value')dnl
popdef(`x_name')dnl
popdef(`x_type')dnl
popdef(`x_scope')dnl
popdef(`x_acessor')dnl
pop_divert()dnl
')

\\ javadoc_args(arg1, arg2, ... argn)
define(`javadoc_args',`dnl
ifelse($#, 0, , $#, 1, `
    * @param `$1'', `javadoc_args(shift($@)')dnl
')

\\ pigfunc_function(`accessor', `scope', `return_type', `return_init_expression', `name', `arguments', `throws' `comment', `function_body')
define(`pigfunc_function',`dnl
push_divert(function_stream)dnl
pushdef(`x_accessor', `$1')dnl
pushdef(`x_scope', `$2')dnl
pushdef(`x_return_type', `$3')dnl
pushdef(`x_return_init_expression', `$4')dnl
pushdef(`x_name', `$5')dnl
pushdef(`x_arguments', `$6')dnl
pushdef(`x_throws', `$7')dnl
pushdef(`x_comment', `$8')dnl
pushdef(`x_function_body', `$9')dnl
    `/**'
x_comment()`'ifelse(x_return_type(), `void', `', `
     * @return ')
    ` */'
    x_accessor() x_scope() x_return_type() x_name()`('x_arguments()`)'ifelse(`x_throws()',`',`',` throws x_throws()') {
ifelse(x_return_type(), `void', `', `
        x_return_type() `result = ' x_return_init_expression()`;'')dnl
ifelse(x_function_body(), `',`dnl
        /* ... TODO  */',
        x_function_body())dnl
ifelse(x_return_type(), `void', `', `
        return result;')
    `}'
dnl
popdef(`x_function_body')dnl
popdef(`x_comment')dnl
popdef(`x_throws')dnl
popdef(`x_arguments')dnl
popdef(`x_name')dnl
popdef(`x_return_init_expression')dnl
popdef(`x_return_type')dnl
popdef(`x_scope')dnl
popdef(`x_accessor')dnl
pop_divert()dnl
')

\\ Create the override of getFunctionName()
define(`pigfunc_get_function_name',`dnl
push_divert(get_function_name_stream)dnl
    /**
     * Get the formal name of the VSMAPI function.
     * @return the formal name of the VSMAPI function.
     */
    @Override
    public String getFunctionName`(') {
        return FUNCTION_NAME;
    }

pop_divert()dnl
')

\\ Close a class definition
define(`pigfunc_endclass',`dnl
pigfunc_get_function_name()dnl
push_divert(class_footer_stream)dnl
}
pop_divert()dnl
')

\\ Start definitions
define(`pigfunc_start',`dnl
push_divert(file_header_stream)dnl
include(`pigiron_copyright_comment.txt')
`/* Autogenerated' date_string()` * by pigfunc.m4 Copyright *C* 2008 Jack J. Woehr'
` * Part of the PigIron Project http://pigiron.sourceforge.net'
` */'
pop_divert()dnl
')

\\ pigfunc_compose_input_start()
define(`pigfunc_compose_input_start',`dnl
push_divert(`compose_in_stream')dnl
    /**
     * Marshall parameters for the VSMAPI function call.
     * "Input" as in "input to VSMAPI".
     * @return the composed input ParameterArray
     * @see #composeOutputArray`(')
     * @see com.softwoehr.pigiron.access.ParameterArray
     */
    protected ParameterArray composeInputArray`(') {
        VSMString tempString = null;
        ParameterArray parameterArray = new ParameterArray`(');
        tempString = new VSMString`('getFunctionName`('), getFunctionName`('));
        parameterArray.add`('new VSMInt4`('tempString.paramLength`('), "function_name_length"));
        parameterArray.add`('tempString);
        tempString = new VSMString`('getUserid`('), "authenticated_userid");
        parameterArray.add`('new VSMInt4`('tempString.paramLength`('), "authenticated_userid_length"));
        parameterArray.add`('tempString);
        tempString = new VSMString`('getPassword`('), "password");
        parameterArray.add`('new VSMInt4`('tempString.paramLength`('), "password_length"));
        parameterArray.add`('tempString);
pop_divert()dnl
')

\\ pigfunc_compose_input_parm(type, value, formal_name)
define(`pigfunc_compose_input_parm',`dnl
push_divert(`compose_in_stream')dnl
pushdef(`x_type', $1)dnl
pushdef(`x_value', $2)dnl
pushdef(`x_formal_name', $3)dnl
ifelse(x_type(),`CountedString',`dnl
        tempString = new VSMString`('x_value, "x_formal_name()");
        parameterArray.add`('new VSMInt4`('tempString.paramLength`('), "x_formal_name()_length"));
        parameterArray.add`('tempString);',`dnl
        parameterArray.add`('new x_type()`('x_value(), "x_formal_name()"`)'`)';dnl
')
popdef(`x_formal_name')dnl
popdef(`x_value')dnl
popdef(`x_type')dnl
pop_divert()dnl
')

\\ pigfunc_compose_optional_input_parm(type, value, formal_name)
define(`pigfunc_compose_optional_input_parm',`dnl
push_divert(`compose_in_stream')dnl
pushdef(`x_type', $1)dnl
pushdef(`x_value', $2)dnl
pushdef(`x_formal_name', $3)dnl
pushdef(`optional_formal_name',`optional_'x_formal_name)dnl
pigfunc_attribute(`public', `', `boolean', `optional_'x_formal_name, `true', `', `true means optional param will be used in VSMAPI function input')dnl
        if (`is_'optional_formal_name`(')) { // Only add if optional param is designated in use by `set_'optional_formal_name`'()
ifelse(x_type(),`CountedString',`dnl
            tempString = new VSMString`('x_value, "x_formal_name()");
            parameterArray.add`('new VSMInt4`('tempString.paramLength`('), "x_formal_name()_length"));
            parameterArray.add`('tempString);',`dnl
            parameterArray.add`('new x_type()`('x_value(), "x_formal_name()"`)';)
')
        }
popdef(`optional_formal_name')dnl
popdef(`x_formal_name')dnl
popdef(`x_value')dnl
popdef(`x_type')dnl
pop_divert()dnl
')

\\ pigfunc_compose_input_end()
define(`pigfunc_compose_input_end',`dnl
push_divert(`compose_in_stream')dnl
        VSMInt4 outputLength = new VSMInt4(new Long(parameterArray.totalParameterLength()).intValue(), "output_length");
        parameterArray.insertElementAt(outputLength, 0);
        setInParams(parameterArray);
        return parameterArray;
    }

pop_divert()dnl
')

\\ pigfunc_compose_output_start()
define(`pigfunc_compose_output_start',`dnl
push_divert(`compose_out_stream')dnl
    /**
     * Marshall parameters for the return of the VSMAPI function call.
     * "output" as in "output from VSMAPI"
     * @return the composed output ParameterArray
     * @see #composeInputArray()
     * @see com.softwoehr.pigiron.access.ParameterArray
     */
    protected ParameterArray composeOutputArray() {
        ParameterArray parameterArray = new ParameterArray();
        parameterArray.add(new VSMInt4(-1, "request_id_immediate"));
        parameterArray.add(new VSMInt4(-1, "output_length"));
        parameterArray.add(new VSMInt4(-1, "request_id"));
        parameterArray.add(new VSMInt4(-1, "return_code"));
        parameterArray.add(new VSMInt4(-1, "reason_code"));
pop_divert()dnl
')

\\ Recognize that a type ends in the string `Array' and treat
\\ it as an Array type in output composition, use VSMArray.modelArray() .
\\ Returns -1 if not a match.
define(`is_type_named_array',`regexp(`$1',`Array$')')

\\ pigfunc_compose_output_parm(type, value, formal_name)
define(`pigfunc_compose_output_parm',`dnl
push_divert(`compose_out_stream')dnl
pushdef(`x_type', $1)dnl
pushdef(`x_value', $2)dnl
pushdef(`x_formal_name', $3)dnl
ifelse(is_type_named_array(x_type),-1,`dnl
        parameterArray.add(new x_type()(x_value(), "x_formal_name()"));',`dnl
        parameterArray.add(x_type()`.modelArray'`('"x_formal_name()"));dnl
')
popdef(`x_formal_name')dnl
popdef(`x_value')dnl
popdef(`x_type')dnl
pop_divert()dnl
')

\\ pigfunc_compose_output_end()
define(`pigfunc_compose_output_end',`dnl
push_divert(`compose_out_stream')dnl
        setOutParams(parameterArray);
        return parameterArray;
    }

pop_divert()dnl
')

\\ End definitions
define(`pigfunc_end',`dnl
push_divert(normal_stream)dnl
undivert(file_header_stream)dnl
undivert(package_stream)dnl
undivert(imports_stream)dnl
undivert(class_header_stream)dnl
undivert(constant_stream)dnl
undivert(ctor_stream)dnl
undivert(attribute_stream)dnl
undivert(accessor_stream)dnl
undivert(compose_in_stream)dnl
undivert(compose_out_stream)dnl
undivert(get_function_name_stream)dnl
undivert(function_stream)dnl
undivert(class_footer_stream)dnl
undivert(file_footer_stream)dnl
pop_divert()dnl
')

\\ End of pigfunc.m4
pop_divert()dnl
