export default class Utils
{
    static getColumnName(x)
    {
        var ordA = 'a'.charCodeAt(0);
        var ordZ = 'z'.charCodeAt(0);

        var len = ordZ - ordA + 1;

        var name = "";

        while (x >= 0)
        {
            name = String.fromCharCode(x % len + ordA) + name;
            x = Math.floor(x / len) - 1;
        }

        return name.toUpperCase();
    }
}