# Dependency Update Migration Guide

This guide covers the migration from older versions to the latest versions of all major dependencies as of February 2026.

## Summary of Updates

| Package | Previous Version | New Version | Breaking Changes |
|---------|-----------------|-------------|------------------|
| react | 18.3.1 | 19.2.4 | ✅ Major |
| react-dom | 18.3.1 | 19.2.4 | ✅ Major |
| antd | 5.21.4 | 6.2.2 | ✅ Major |
| axios | 1.7.7 | 1.13.4 | ⚠️ Minor |
| @types/react | 18.3.11 | 19.2.10 | ✅ Major |
| @types/react-dom | 18.3.1 | 19.2.3 | ✅ Major |
| @types/jest | 29.5.13 | 30.0.0 | ✅ Major |
| @types/node | 22.7.5 | 25.1.0 | ✅ Major |
| @testing-library/jest-dom | 6.5.0 | 6.9.1 | ⚠️ Patch |
| @testing-library/react | 16.0.1 | 16.3.2 | ⚠️ Patch |
| jest | 29.7.0 | 30.2.0 | ✅ Major |
| webpack | 5.95.0 | 5.104.1 | ⚠️ Patch |
| core-js | 3.38.1 | 3.48.0 | ⚠️ Minor |

## Installation Steps

### 1. Install Updated Dependencies

```bash
npm install
```

If you encounter peer dependency conflicts, you may need to use:

```bash
npm install --legacy-peer-deps
```

### 2. Run Migration Codemods

#### React 19 Migration

Run the automated codemod to update React 19 patterns:

```bash
npx codemod@latest react/19/migration-recipe
```

#### TypeScript Types Migration

Update TypeScript types for React 19:

```bash
npx types-react-codemod@latest preset-19 ./src
```

## Breaking Changes & Required Code Updates

### React 19 Changes

#### 1. Component Return Types

React 19 no longer allows returning `undefined` from components. You must return `null` instead.

```typescript
// ❌ Before (React 18)
function Component() {
  if (condition) return undefined;
  return <div>Content</div>;
}

// ✅ After (React 19)
function Component() {
  if (condition) return null;
  return <div>Content</div>;
}
```

#### 2. `ref` as a Prop

`ref` is now a regular prop and doesn't require `forwardRef` in many cases:

```typescript
// ❌ Before (React 18)
const MyInput = forwardRef((props, ref) => {
  return <input ref={ref} />;
});

// ✅ After (React 19)
function MyInput({ ref, ...props }) {
  return <input ref={ref} {...props} />;
}
```

#### 3. Context Provider Simplified

```typescript
// ❌ Before (React 18)
<MyContext.Provider value={value}>
  {children}
</MyContext.Provider>

// ✅ After (React 19) - Both work, but this is simpler
<MyContext value={value}>
  {children}
</MyContext>
```

### Ant Design 6 Changes

#### 1. Deprecated Components

Several components have been moved or renamed:

```typescript
// ❌ BackTop is deprecated
import { BackTop } from 'antd';
<BackTop />

// ✅ Use FloatButton.BackTop
import { FloatButton } from 'antd';
<FloatButton.BackTop />
```

**Currently used in your app:** None detected in current codebase.

#### 2. Menu Component Changes

The `SubMenu` API has been updated. Your code in `MainTab.tsx` uses:

```typescript
// Current usage (needs verification with v6)
const { SubMenu } = Menu;

<SubMenu key="sub1" title="Свердловский участок">
  <Menu.Item key="1">Березит</Menu.Item>
</SubMenu>
```

In Ant Design 6, verify that `SubMenu` still works or use the `items` prop pattern:

```typescript
// ✅ Recommended v6 pattern
<Menu
  mode="inline"
  defaultSelectedKeys={['1']}
  items={[
    {
      key: 'sub1',
      label: 'Свердловский участок',
      children: [
        { key: '1', label: 'Березит' },
        { key: '2', label: 'Кедровка' },
        { key: '3', label: 'Монетная' },
        { key: '4', label: 'Копалуха' },
      ],
    },
  ]}
/>
```

#### 3. Tabs Component Changes

Your code uses deprecated `TabPane`:

```typescript
// ❌ Before (v5)
const { TabPane } = Tabs;
<Tabs>
  <TabPane tab="Tab 1" key="1">Content</TabPane>
</Tabs>

// ✅ After (v6)
<Tabs
  items={[
    { key: '1', label: 'Tab 1', children: 'Content' },
  ]}
/>
```

**Action Required:** Update `MainTab.tsx` to use the `items` prop pattern.

### Axios Changes

#### 1. CancelToken is Deprecated

Replace `CancelToken` with `AbortController`:

```typescript
// ❌ Before (deprecated)
const CancelToken = axios.CancelToken;
const source = CancelToken.source();

axios.get('/api/data', {
  cancelToken: source.token
});

source.cancel('Cancelled by user');

// ✅ After (recommended)
const controller = new AbortController();

axios.get('/api/data', {
  signal: controller.signal
});

controller.abort();
```

**Currently used in your app:** No axios requests are currently implemented in the codebase, so no changes needed yet.

## Specific File Updates Required

### 1. `src/components/mainTab/MainTab.tsx`

Update the Tabs component usage:

```typescript
// Before
import {Breadcrumb, Col, Layout, Menu, Row, Space, Tabs} from 'antd';
const {SubMenu} = Menu;
const {Header, Content, Footer, Sider} = Layout;
const {TabPane} = Tabs;

// After
import {Breadcrumb, Col, Layout, Menu, Row, Space, Tabs} from 'antd';
const {Header, Content, Footer, Sider} = Layout;
// Remove SubMenu and TabPane destructuring
```

Then update the JSX to use the `items` prop for both Menu and Tabs components.

### 2. TypeScript Configuration

Your `tsconfig.json` might need updates for React 19:

```json
{
  "compilerOptions": {
    "jsx": "react-jsx",  // Changed from "react"
    "lib": ["dom", "dom.iterable", "esnext"],
    // ... other options
  }
}
```

## Testing Your Changes

After migration:

1. **Build the application:**
   ```bash
   npm run build
   ```

2. **Run tests:**
   ```bash
   npm test
   ```

3. **Start the development server:**
   ```bash
   npm start
   ```

4. **Verify functionality:**
   - Check that all tabs render correctly
   - Verify menu navigation works
   - Ensure RelayCard components display properly
   - Test all interactive elements

## Common Issues & Solutions

### Issue: Peer Dependency Conflicts

If you see peer dependency warnings:

```bash
npm install --legacy-peer-deps
```

### Issue: TypeScript Errors with React Types

Clear your TypeScript cache:

```bash
rm -rf node_modules
rm package-lock.json
npm install
```

### Issue: Ant Design Components Not Rendering

Check that you're importing components correctly. Some components may have moved to new packages in v6.

### Issue: Build Failures

If the build fails due to webpack or babel issues, you may need to update your build configuration or wait for `react-scripts` to release a version compatible with React 19.

## Rollback Plan

If you encounter critical issues, you can rollback by reverting `package.json` changes:

```bash
git checkout package.json
npm install
```

## Additional Resources

- [React 19 Upgrade Guide](https://react.dev/blog/2024/04/25/react-19-upgrade-guide)
- [Ant Design 6 Migration Documentation](https://ant.design/docs/react/migration-v6)
- [Axios Documentation](https://axios-http.com/docs/intro)

## Next Steps

1. ✅ Update package.json (completed)
2. ⏳ Run `npm install`
3. ⏳ Run React 19 migration codemods
4. ⏳ Update `MainTab.tsx` for Ant Design 6
5. ⏳ Update `tsconfig.json` for React 19
6. ⏳ Test the application
7. ⏳ Fix any remaining TypeScript errors
8. ⏳ Update CLAUDE.md with new patterns
