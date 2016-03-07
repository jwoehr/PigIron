divert(-1)
changecom(`\\\\')
\\ pigbuilderfunc.m4
\\   Macros to autogenerate Java code for PigIron web builder functions
\\   Copyright *C* 1999, 2001, 2008, 2009, 2016 Jack J. Woehr
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

\\ First, redefine this pigsty macro so it does NOT convert its arguments
pushdef(`member_name',`$1')

\\ pigfunc_import(`import_name')
define(`pigfunc_import',`')

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
package com.softwoehr.piglet.builder.functions;

pop_divert()dnl
x_comment()
public class x_name() extends BuilderFunctionProxy {


popdef(`x_comment')dnl
popdef(`x_function_name')dnl
popdef(`x_package')dnl
popdef(`x_extends')dnl
popdef(`x_name')dnl
pop_divert()dnl
')

\\ optional_ctor_params_comments(type, name, instance, t, n, i ...)
\\ Because the big ctor has to be extendable and must instance vars
define(`optional_ctor_param_comments',`')

\\ optional_ctor_params_args(type, name, instance, t, n, i ...)
\\ Because the big ctor has to be extendable and must instance vars
define(`optional_ctor_param_args',`')

\\ optional_ctor_param_instantiations(type, name, instance, t, n, i ...)
\\ Because the big ctor has to be extendable and must instance vars
define(`optional_ctor_param_instantiations',`')

\\ pigfunc_ctors(OPTIONAL type, name, instance, t, n, i ..)
define(`pigfunc_ctors',`dnl
push_divert(ctor_stream)dnl
    /**
     * Constructor does nothing.
     */ 
    public myClassName()`('`)' { super();}

    /**
     *  Gets the parameters array for this BuilderFunctionProxy extender
     *
     * @return    The parameters that need form building and filling in
     */ 
    public Parameter [] getParameters() {
        return new Parameter [] {
ifelse(myClassName(),`CheckAuthentication',`',`dnl
            new Parameter("target_identifier", "", "Target of operation")')dnl
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
pushdef(`x_comment', translit($7,`"'`'))dnl
pushdef(`x_qualified_name', x_accessor()x_scope()x_type() x_name())dnl
pushdef(`x_get_accessor_name', ifelse(x_type, `boolean', `is_', `get_')`'x_name())dnl
`,'
            new Parameter("x_name()", x_initial_value(), "x_comment()")dnl
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
define(`javadoc_args',`')

\\ pigfunc_function(`accessor', `scope', `return_type', `return_init_expression', `name', `arguments', `throws' `comment', `function_body')
define(`pigfunc_function',`')

\\ Create the override of getFunctionName()
define(`pigfunc_get_function_name',`')

\\ Close a class definition
define(`pigfunc_endclass',`dnl
pigfunc_get_function_name()dnl
push_divert(class_footer_stream)dnl
 } ;
    }
}
pop_divert()dnl
')

\\ Start definitions
define(`pigfunc_start',`dnl
push_divert(file_header_stream)dnl
include(`pigiron_copyright_comment.txt')
`/* Autogenerated' date_string()` * by pigbuilderfunc.m4 Copyright *C* 2016 Jack J. Woehr'
` * Part of the PigIron Project http://pigiron.sourceforge.net'
` */'
pop_divert()dnl
')

\\ pigfunc_compose_input_start()
define(`pigfunc_compose_input_start',`')

\\ pigfunc_compose_input_parm(type, value, formal_name)
define(`pigfunc_compose_input_parm',`')

\\ pigfunc_compose_optional_input_parm(type, value, formal_name)
define(`pigfunc_compose_optional_input_parm',`')

\\ pigfunc_compose_input_end()
define(`pigfunc_compose_input_end',`')

\\ pigfunc_compose_output_start()
define(`pigfunc_compose_output_start',`')

\\ pigfunc_compose_output_snippet(`code snippet')
define(`pigfunc_compose_output_snippet',`')

\\ pigfunc_compose_output_parm(type, value, formal_name)
define(`pigfunc_compose_output_parm',`')

\\ pigfunc_compose_output_end()
define(`pigfunc_compose_output_end',`')

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
