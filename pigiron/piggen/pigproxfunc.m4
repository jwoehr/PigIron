divert(-1)
changecom(`\\\\')
\\ pigproxfunc.m4
\\   Macros to autogenerate Java code for PigIron JSONized proxy functions
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
dnl \\ import $1;
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
package regexp(x_package(),`\(.*\)\.functions',`\1\.webobj\.topview\.functions')`;'

import `com.softwoehr.pigiron.webobj.topview.*';
import `com.softwoehr.pigiron.webobj.topview.functions.FunctionProxy';

pop_divert()dnl
/**
 * Proxy `function' class to bridge JSON to PigIron
 *
 */
public class x_name() extends FunctionProxy {

popdef(`x_comment')dnl
popdef(`x_function_name')dnl
popdef(`x_package')dnl
popdef(`x_extends')dnl
popdef(`x_name')dnl
pop_divert()dnl
')

\\ optional_execute_params_comments(type, name, instance, t, n, i ...)
\\ Because instancing in the execute can have many args.
define(`optional_execute_param_comments',`dnl
ifelse(eval($# >= 3), 0, , eval($# == 3), 1, `
     *   --  `$2' instances {@code `$1'}', `
     *   --  `$2' instances {@code `$1'}`'optional_execute_param_comments(shift(shift(shift($@))))')dnl
')

\\ optional_ctor_params_args(type, name, instance, t, n, i ...)
\\ Because the big ctor has to be extendable and must instance vars
define(`optional_ctor_param_args',`dnl
ifelse(eval($# >= 3), 0, , eval($# == 3), 1, `,' ``$1' `$2'', `,' ``$1' `$2'optional_ctor_param_args(shift(shift(shift($@))))')')

\\ gen_opt_param_spec(type,name)
define(`gen_opt_param_spec',`dnl
ifelse($1,`String',`getInputArgumentString`('"$2"`)'',`dnl
ifelse($1,`int',`getInputArgumentLong`('"$2"`)'')')')

\\ optional_execute_param_instantiations(type, name, instance, t, n, i ...)
\\ Because instancing in the execute can have many args.
define(`optional_execute_param_instantiations',`dnl
ifelse(eval($# >= 3), 0, , eval($# == 3), 1, `
         `,' gen_opt_param_spec($1,$2)',`
         `,' gen_opt_param_spec($1,$2)`'dnl
optional_execute_param_instantiations(shift(shift(shift($@))))')dnl
')

\\ pigfunc_ctors(OPTIONAL type, name, instance, t, n, i ..)
define(`pigfunc_ctors',`dnl
push_divert(ctor_stream)dnl
pushdef(`x_optional_params', `$@')dnl
    /**
     *  Create an instance of the call with empty defaults
     */
    // public myClassName()`('`)' { super`('`)'; }

    /**
     * Create an instance of the function`' proxy with requestor and response instanced.
     * It will consume the requestor in execution and return the response suitably modified.
     * @param requestor the requestor spawning the instance execution
     * @param response the response to be modified and returned in the execution
     */
    public myClassName()`('Requestor requestor`,' Response response`)' throws org.json.JSONException {
        super`('requestor`,'response`)';
    }

    /**
     * Execute the PigIron VSMAPI call we have set up in this instance.
     *
     * @return                             the response from the call
     * @exception  org.json.JSONException  on JSON err
     *
     * The PigIron/VSMAPI parameters fed to the instancing within execute`()' are as follows:
     *   --  hostname  VSMAPI Host DNS name
     *   --  port port VSMAPI Host is listening on
     *   --  userid userid executing the function`'
     *   --  password the password
     *   --  target_identifier the target of the VSMAPI function`'dnl
dnl regexp(optional_ctor_param_comments(x_optional_params()),`\(.*\)@param \(.*\)', `\1  --  \2')
optional_execute_param_comments(x_optional_params())
     */ 
    public Response execute`()' throws org.json.JSONException {
        com.softwoehr.pigiron.functions.myClassName() pigfunc = new com.softwoehr.pigiron.functions.myClassName()
	`('
           getHostSpecifier`()'
         `,' host.getPortNumber`()'
         `,' user.getUid`()'
         `,' user.getPassword`()'
         `,' getTargetIdentifier`()'dnl
optional_execute_param_instantiations(x_optional_params)
	`);'
        execute`(pigfunc,requestor,response)';
        return response;
    }
       
popdef(`x_optional_params')dnl
pop_divert()dnl
')

\\ pigfunc_constant(`accessor', `type', `name', `initial_value', `comment')
define(`pigfunc_constant',`dnl
')

\\ pigfunc_attribute(`accessor', `scope', `type', `name', `initial_value', `set_accessor', `comment')
define(`pigfunc_attribute',`dnl
')

\\ javadoc_args(arg1, arg2, ... argn)
define(`javadoc_args',`dnl
ifelse($#, 0, , $#, 1, `
    * @param `$1'', `javadoc_args(shift($@)')dnl
')

\\ pigfunc_function(`accessor', `scope', `return_type', `return_init_expression', `name', `arguments', `throws' `comment', `function_body')
define(`pigfunc_function',`dnl
')

\\ Create the override of getFunctionName()
define(`pigfunc_get_function_name',`dnl
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
`/* Autogenerated' date_string()` * by pigproxfunc.m4 Copyright *C* 2008 Jack J. Woehr'
` * Part of the PigIron Project http://pigiron.sourceforge.net'
` */'
pop_divert()dnl
')

\\ pigfunc_compose_input_start()
define(`pigfunc_compose_input_start',`dnl
')

\\ pigfunc_compose_input_parm(type, value, formal_name)
define(`pigfunc_compose_input_parm',`dnl
')

\\ pigfunc_compose_optional_input_parm(type, value, formal_name)
define(`pigfunc_compose_optional_input_parm',`dnl
')

\\ pigfunc_compose_input_end()
define(`pigfunc_compose_input_end',`dnl
')

\\ pigfunc_compose_output_start()
define(`pigfunc_compose_output_start',`dnl
')

\\ Recognize that a type ends in the string `Array' and treat
\\ it as an Array type in output composition, use VSMArray.modelArray() .
\\ Returns -1 if not a match.
define(`is_type_named_array',`regexp(`$1',`Array$')')

\\ pigfunc_compose_output_parm(type, value, formal_name)
define(`pigfunc_compose_output_parm',`dnl
')

\\ pigfunc_compose_output_end()
define(`pigfunc_compose_output_end',`dnl
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
