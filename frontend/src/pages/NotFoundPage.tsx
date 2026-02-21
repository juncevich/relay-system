import {Button, Result} from 'antd';
import {Link} from 'react-router';

function NotFoundPage() {
    return (
        <Result
            status="404"
            title="404"
            subTitle="Страница не найдена"
            extra={
                <Link to="/">
                    <Button type="primary">На главную</Button>
                </Link>
            }
        />
    );
}

export default NotFoundPage;
