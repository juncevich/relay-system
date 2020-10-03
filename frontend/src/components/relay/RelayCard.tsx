import React from 'react';
import {Card} from 'antd';
import {CloseCircleFilled, EditOutlined, EllipsisOutlined, SettingOutlined} from '@ant-design/icons';
import Meta from 'antd/es/card/Meta';

class RelayCard extends React.Component<any, any> {

    render() {
        const card = <Card
            style={{width: 150}}
            cover={
                <img
                    alt="example"
                    src="https://lh3.googleusercontent.com/proxy/Wg_x4GNfLG6R13LUSdanmVfZGj3Zr9RI5Q5WonJ5phQgZVOVQyVpvbm6n0M6BygZxcUOmBeV_c3fr5bM2AGfNkIvnyFTQzV_oBHA6DWWOp3wZCNlE7f8iK7SF8ft0otTs8tskvVrCA"
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
                title="НМШ-400"
            />
            <table>
                <tr>
                    <td>Дата проверки</td>
                    <td>01.02.2003</td>
                </tr>
            </table>
        </Card>;
        return (
            <span>
                {card}
            </span>
        );
    }
}

export default RelayCard;
