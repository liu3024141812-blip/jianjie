/* 改这里 = 改秒杀结束时间（某天 某时 某分 某秒） */
var deadline = new Date('2027-10-11T00:00:00');

function tick() {
  var rest = deadline - new Date();          // 剩余毫秒
  if (rest < 0) rest = 0;                    // 时间到就停在 0

  var s = Math.floor(rest / 1000);
  var d = Math.floor(s / 86400);             // 天
  var h = Math.floor(s % 86400 / 3600);      // 时
  var m = Math.floor(s % 3600 / 60);         // 分
  var sec = s % 60;                          // 秒

  document.getElementById('dd').textContent = d;
  document.getElementById('hh').textContent = h < 10 ? '0' + h : h;
  document.getElementById('mm').textContent = m < 10 ? '0' + m : m;
  document.getElementById('ss').textContent = sec < 10 ? '0' + sec : sec;
}

tick();                 // 先执行一次，避免首屏闪 00
setInterval(tick, 1000);
