import React from 'react';
import {Card} from 'antd';
import {CloseCircleFilled, EditOutlined, EllipsisOutlined, SettingOutlined} from '@ant-design/icons';
import './RelayCard.css';
import Relay from '../../models/Relay';

const { Meta } = Card;

const RelayCard: React.FC<{ relay?: Relay }> = (props) => {

    return (
        <>
        <span>
                <div className="relay-card-container">
                <Card
                    // style={{width: "auto"}}
                    cover={
                        <img
                            alt="example"
                            src={props.relay?.imgUrl}
                            // src="http://www.status-scb.ru/upload/iblock/458/458aa8a30c03af897511a2d8c00cdc74.png"
                        />
                    }
                    actions={[
                        <SettingOutlined key="setting"/>,
                        <EditOutlined key="edit"/>,
                        <EllipsisOutlined key="ellipsis"/>,
                    ]}
                >
            <Meta
                avatar={<CloseCircleFilled style={{color: '#08c'}}/>}
                title={props.relay?.title}
                // title="НМШ-400"
            />
            <table>
                <tr>
                    <td>Дата проверки</td>
                    <td>{props.relay?.checkingDate}</td>
                    {/*<td>01.02.2003</td>*/}
                </tr>
            </table>
        </Card>;
                </div>
            </span>
        </>
    );
};

// class RelayCard extends React.Component<any, any> {
//
//     render() {
//         const card = <Card
//             // style={{width: "auto"}}
//             cover={
//                 <img
//                     alt="example"
//                     src="http://www.status-scb.ru/upload/iblock/458/458aa8a30c03af897511a2d8c00cdc74.png"
//                 />
//             }
//             actions={[
//                 <SettingOutlined key="setting"/>,
//                 <EditOutlined key="edit"/>,
//                 <EllipsisOutlined key="ellipsis"/>,
//             ]}
//         >
//             <Meta
//                 avatar={<CloseCircleFilled style={{color: '#08c'}}/>}
//                 title="НМШ-400"
//             />
//             <table>
//                 <tr>
//                     <td>Дата проверки</td>
//                     <td>01.02.2003</td>
//                 </tr>
//             </table>
//         </Card>;
//         return (
//             <span>
//                 <div className="relay-card-container">
//                 {card}
//                 </div>
//             </span>
//         );
//     }
// }

export default RelayCard;
