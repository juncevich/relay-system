import {Link} from 'react-router';
import './HomePage.css';

const stats = [
    {label: 'Всего реле', value: '—', color: '#00c8d4'},
    {label: 'Проверено', value: '—', color: '#4ade80'},
    {label: 'Скоро проверка', value: '—', color: '#fbbf24'},
    {label: 'Просрочено', value: '—', color: '#f87171'},
];

const quickLinks = [
    {
        to: '/relays',
        icon: '⚡',
        color: '#00c8d4',
        title: 'Управление реле',
        desc: 'Просмотр, добавление и редактирование путевых реле',
    },
    {
        to: '/stations',
        icon: '🏢',
        color: '#4ade80',
        title: 'Станции',
        desc: 'Управление станциями, перегонами и переездами',
    },
];

function HomePage() {
    return (
        <div className="home">
            <div className="home__hero">
                <div className="home__hero-glow" aria-hidden="true"/>
                <div className="home__hero-grid" aria-hidden="true"/>

                <div className="home__hero-content">
                    <div className="home__chip">
                        <span className="home__chip-dot"/>
                        Система управления
                    </div>

                    <h1 className="home__title">
                        Relay
                        <span className="home__title-accent">System</span>
                    </h1>

                    <p className="home__desc">
                        Платформа мониторинга и управления путевыми реле.
                        Контроль состояния, история проверок, управление парком
                        оборудования на всех объектах.
                    </p>

                    <div className="home__actions">
                        <Link to="/relays" className="home__btn home__btn--primary">
                            Реле
                        </Link>
                        <Link to="/stations" className="home__btn home__btn--secondary">
                            Станции
                        </Link>
                    </div>
                </div>
            </div>

            <div className="home__stats">
                {stats.map(s => (
                    <div
                        key={s.label}
                        className="home__stat"
                        style={{borderTopColor: s.color}}
                    >
                        <div className="home__stat-value" style={{color: s.color}}>
                            {s.value}
                        </div>
                        <div className="home__stat-label">{s.label}</div>
                    </div>
                ))}
            </div>

            <div className="home__links">
                {quickLinks.map(l => (
                    <Link key={l.to} to={l.to} className="home__link-card">
                        <div
                            className="home__link-icon"
                            style={{
                                background: `${l.color}14`,
                                borderColor: `${l.color}40`,
                                color: l.color,
                            }}
                        >
                            {l.icon}
                        </div>
                        <div className="home__link-body">
                            <div className="home__link-title">{l.title}</div>
                            <div className="home__link-desc">{l.desc}</div>
                        </div>
                        <span className="home__link-arrow">›</span>
                    </Link>
                ))}
            </div>
        </div>
    );
}

export default HomePage;
