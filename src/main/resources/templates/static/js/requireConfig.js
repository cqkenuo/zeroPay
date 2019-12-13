/**
 * requirejs的通用配置。
 */
require.config({
    baseUrl: 'static/vendors',
    paths: {
        blockUI: 'blockui/jquery.blockUI.min',
        bootbox: 'bootbox/bootbox.min',
        bootstrap: 'bootstrap/js/bootstrap.min',
        'datatables.net': 'datatables/js/jquery.dataTables.min',
        'datatables.net-bs': 'datatables/js/dataTables.bootstrap.min',
        'datatables.net-responsive': 'datatables/js/dataTables.responsive.min',
        datatables: 'datatables/js/responsive.bootstrap.min',
        datetimepicker: 'bootstrap-datetimepicker/bootstrap-datetimepicker.min',
        fileinput: 'bootstrap-fileinput/js/fileinput',
        fileinputZhCn: 'bootstrap-fileinput/js/zh',
        gentelella: 'gentelella/custom.min',
        lightbox: 'ekko-lightbox/ekko-lightbox.min',
        handlebars: 'handlebars/handlebars.amd.min',
        jquery: 'jquery/jquery.min',
        'js-url': 'js-url/url.min',
        moment: 'moment/moment-with-locales.min',
        multiselect: 'multiselect/js/bootstrap-multiselect',
        parsley: 'parsley/parsley.min',
        parsleyZhCn: 'parsley/i18n/zh_cn',
        text: 'requirejs/text.min',
    },
    //shim
    shim: {
        'blockUI': {
            deps: ['jquery']
        },
        'bootbox': {
            deps: ['bootstrap']
        },
        'bootstrap': {
            deps: ['jquery']
        },
        'fileinputZhCn': {
            deps: ['fileinput']
        },
        'gentelella': {
            deps: ['jquery', 'bootstrap']
        },
        'lightbox': {
            deps: ['bootstrap']
        },
        'moment': {
            deps: ['jquery']
        },
        'multiselect': {
            deps: ['bootstrap']
        },
        'parsley': {
            deps: ['jquery']
        },
        'parsleyZhCn': {
            deps: ['parsley']
        }
    }
});