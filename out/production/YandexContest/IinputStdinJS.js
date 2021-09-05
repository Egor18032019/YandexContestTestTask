sum = (data) => {
    let mas = data.toString().split(' ');
    return +mas[0] + +mas[1];
};

let res = 0;

process.stdin.on('data', data => {
    res = sum(data);
    process.stdout.write(res + '');
    process.exit();
});