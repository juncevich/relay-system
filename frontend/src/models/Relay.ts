// Legacy Relay model - kept for backward compatibility with existing UI components
class Relay {
    id: number;
    imgUrl: string;
    title: string;
    checkingDate: string;
    storageId?: number;

    constructor(id: number, imgUrl: string, title: string, checkingDate: string, storageId?: number) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.title = title;
        this.checkingDate = checkingDate;
        this.storageId = storageId;
    }

    // Helper method to create a Relay from backend data
    static fromBackendRelay(backendRelay: {
        id: number;
        serialNumber: string;
        lastCheckDate?: string;
        relayType?: string;
        storageId?: number;
    }): Relay {
        return new Relay(
            backendRelay.id,
            // Default image URL for relays
            'http://www.status-scb.ru/upload/iblock/458/458aa8a30c03af897511a2d8c00cdc74.png',
            backendRelay.serialNumber,
            backendRelay.lastCheckDate
                ? new Date(backendRelay.lastCheckDate).toLocaleDateString('ru-RU')
                : 'Не проверено',
            backendRelay.storageId
        );
    }
}

export default Relay;
