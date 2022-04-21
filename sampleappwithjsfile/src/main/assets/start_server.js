const http = require('http');

var startMode = 'normal';
if (process.argv[2] !== undefined && process.argv[2].startsWith('mode=')) {
  startMode = process.argv[2].substring(5);
}
console.log('Start mode - ' + startMode);

const requestListener = function (req, res) {
  res.writeHead(200);
  res.end('Hello, World from using mode ' + startMode);
}

const server = http.createServer(requestListener);
server.listen(8080);