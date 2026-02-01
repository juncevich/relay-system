import Relay from './Relay';
import { mockBackendRelay, mockBackendRelayWithoutCheckDate } from '../test-utils/mockData';

describe('Relay Model', () => {
    describe('constructor', () => {
        it('should create a relay instance with all properties', () => {
            const relay = new Relay(
                1,
                'http://example.com/image.png',
                'REL-001',
                '01.02.2024'
            );

            expect(relay.id).toBe(1);
            expect(relay.imgUrl).toBe('http://example.com/image.png');
            expect(relay.title).toBe('REL-001');
            expect(relay.checkingDate).toBe('01.02.2024');
        });
    });

    describe('fromBackendRelay', () => {
        it('should convert backend relay with lastCheckDate to legacy relay', () => {
            const relay = Relay.fromBackendRelay(mockBackendRelay);

            expect(relay.id).toBe(mockBackendRelay.id);
            expect(relay.title).toBe(mockBackendRelay.serialNumber);
            expect(relay.imgUrl).toBe('http://www.status-scb.ru/upload/iblock/458/458aa8a30c03af897511a2d8c00cdc74.png');
            expect(relay.checkingDate).toBe('01.02.2024');
        });

        it('should handle relay without lastCheckDate', () => {
            const relay = Relay.fromBackendRelay(mockBackendRelayWithoutCheckDate);

            expect(relay.id).toBe(mockBackendRelayWithoutCheckDate.id);
            expect(relay.title).toBe(mockBackendRelayWithoutCheckDate.serialNumber);
            expect(relay.checkingDate).toBe('Не проверено');
        });

        it('should format date in Russian locale', () => {
            const backendRelay = {
                ...mockBackendRelay,
                lastCheckDate: '2024-12-25T10:00:00Z'
            };

            const relay = Relay.fromBackendRelay(backendRelay);

            expect(relay.checkingDate).toMatch(/25\.12\.2024/);
        });

        it('should use default image URL for all relays', () => {
            const relay = Relay.fromBackendRelay(mockBackendRelay);

            expect(relay.imgUrl).toBe('http://www.status-scb.ru/upload/iblock/458/458aa8a30c03af897511a2d8c00cdc74.png');
        });
    });
});
