//包装函数
module.exports = function(grunt){
    //任务配置，所有插件的配置信息
    grunt.initConfig({
        //获取package.json的信息
        pkg : grunt.file.readJSON('package.json'),
         jshint: {
            build: ['Gruntfile.js','src/**.js'],
            jshintrc: '.jshintrc'
        },
        watch: {
            build: {
                files: ['src/**.js','src/**.css'],
                tasks: ['jshint','uglify'],
                options: {
                    spawn: false,
                     event: ['changed']  // 只在文件修改的时候触发，添加或者删除文件的时候不触发
                }
            }
        },
        //uglify插件配置I信息
        uglify: {
            options: {
               // stripBanners: true,
                 banner: '/*! <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> */\n'//添加banner
            },
            build: {
          
                src: 'src/main/webapp/js/common.js',
                dest: 'build/js/<%=pkg.name%>-<%=pkg.version%>.min.js'
                
            }
        }
       
    });
    //告诉grunt我们使用的插件
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-watch');

    //告诉grunt 我们在终端输入grunt的时候需要做什么（注意先后顺序）
    grunt.registerTask('default',['jshint','uglify']);
};

























