require('bendystraw')({
  paths: {
    build: 'public',
    scripts: 'app'
  },

  angular: {
    enabled: true,
    templateModule: 'bendy.templates',
    envModule: 'bendy.env',
  },

  browserSync: {
    open: false
  },

  deploy: {
    cache: true
  },

  styles: {
    sourcemaps: false,
  },

  build: {
    uncss: true
  },

  scripts: {
    babel: true
  },
})
