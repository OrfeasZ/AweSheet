import * as MessageType from '../constants/MessageTypes'

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

    static dispatchMessage(type, data)
    {
        let message = {
            ...data,
            type: type
        };

        window.aweQuery({ request: this.getMessageClass(type) + ';' + JSON.stringify(message) });
    }

    static getMessageClass(type)
    {
        let className = '';

        switch (type)
        {
            case MessageType.SET_CELL_VALUE:
                className = 'SetCellValueMessage';
                break;

            case MessageType.CREATE_SHEET:
                className = 'CreateSheetMessage';
                break;

            case MessageType.SELECT_SHEET:
                className = 'SelectSheetMessage';
                break;

            case MessageType.DELETE_SHEET:
                className = 'DeleteSheetMessage';
                break;

            case MessageType.SET_SELECTED_CELLS:
                className = 'SetSelectedCellsMessage';
                break;

            case MessageType.CREATE_BAR_CHART:
                className = 'CreateBarChartMessage';
                break;

            case MessageType.CREATE_LINE_CHART:
                className = 'CreateLineChartMessage';
                break;

            default:
                className = 'UIMessage';
                break;
        }

        return 'com.awesheet.messages.' + className;
    }
}